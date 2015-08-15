package com.tarena.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.tarena.entity.PageInfo;
/**
 *它根据当前的Interceptor上面的注解定义哪些接口需要拦截，然后判断当前目标对象是否有实现对应需要拦截的接口，
 *如果没有则返回目标对象本身，如果有则返回一个代理对象。
 *而这个代理对象的InvocationHandler正是一个Plugin。所以当目标对象在执行接口方法时，
 *如果是通过代理对象执行的，则会调用对应InvocationHandler的invoke方法，也就是Plugin的invoke方法。
 *所以接着我们来看一下该invoke方法的内容。这里invoke方法的逻辑是：如果当前执行的方法是定义好的需要拦截的方法，
 *则把目标对象、要执行的方法以及方法参数封装成一个Invocation对象，再把封装好的Invocation作为参数传递给当前
 *拦截器的intercept方法。如果不需要拦截，则直接调用当前的方法。Invocation中定义了定义了一个proceed方法，
 *其逻辑就是调用当前方法，所以如果在intercept中需要继续调用当前方法的话可以调用invocation的procced方法。

       这就是Mybatis中实现Interceptor拦截的一个思想，如果用户觉得这个思想有问题或者不能完全满足你的要求的话可以通过实现
       自己的Plugin来决定什么时候需要代理什么时候需要拦截。以下讲解的内容都是基于Mybatis的默认实现即通过Plugin来管理
       Interceptor来讲解的。 
 *
 */

/**
 * 
 *在Mybatis中Statement语句是通过RoutingStatementHandler对象的prepare方法生成的。
 *所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，
 *然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用StatementHandler对象
 *的prepare方法，即调用invocation.proceed()。
 */

//第二个@Signature我们定义了该Interceptor将拦截StatementHandler中参数类型为Connection的prepare方法

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {

    private static String dialect = "";
    private static String pageSqlId = "";

    @SuppressWarnings("unchecked")
    public Object intercept(Invocation ivk) throws Throwable {

        if (ivk.getTarget() instanceof RoutingStatementHandler) {
        	//是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
                    .getTarget();
          //通过反射获取到当前RoutingStatementHandler对象的delegate属性 
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
                    .getValueByFieldName(statementHandler, "delegate");
            
            
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性  
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper
                    .getValueByFieldName(delegate, "mappedStatement");

            
            if (mappedStatement.getId().matches(pageSqlId)) {
            	//拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象  
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject error");
                } else {
                	//拦截到的prepare方法参数是一个Connection对象  
                    Connection connection = (Connection) ivk.getArgs()[0];
                  //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句  
                    String sql = boundSql.getSql();
                    String countSql = "select count(0) from (" + sql + ") myCount";
                  //  System.out.println("总数sql 语句:"+countSql);
                    PreparedStatement countStmt = connection
                            .prepareStatement(countSql);
                    //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象parameterObject建立查询记录数对应的BoundSql对象。  
                    BoundSql countBS = new BoundSql(
                            mappedStatement.getConfiguration(), countSql,
                            boundSql.getParameterMappings(), parameterObject);
                    
                    setParameters(countStmt, mappedStatement, countBS,
                            parameterObject);
                    ResultSet rs = countStmt.executeQuery();
                    int count = 0;
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                    rs.close();
                    countStmt.close();

                    PageInfo page = null;
                    if (parameterObject instanceof PageInfo) {
                        page = (PageInfo) parameterObject;
                        page.setTotalResult(count);
                    } else if(parameterObject instanceof Map){
                        Map<String, Object> map = (Map<String, Object>)parameterObject;
                        page = (PageInfo)map.get("page");
                        if(page == null)
                            page = new PageInfo();
                        page.setTotalResult(count);
                    }else {
                        Field pageField = ReflectHelper.getFieldByFieldName(
                                parameterObject, "page");
                        if (pageField != null) {
                            page = (PageInfo) ReflectHelper.getValueByFieldName(
                                    parameterObject, "page");
                            if (page == null)
                                page = new PageInfo();
                            page.setTotalResult(count);
                            ReflectHelper.setValueByFieldName(parameterObject,
                                    "page", page);
                        } else {
                            throw new NoSuchFieldException(parameterObject
                                    .getClass().getName());
                        }
                    }
                    String pageSql = generatePageSql(sql, page);
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }
        return ivk.proceed();
    }

    private void setParameters(PreparedStatement ps,
            MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
    	
        ErrorContext.instance().activity("setting parameters")
                .object(mappedStatement.getParameterMap().getId());
      //通过BoundSql获取对应的参数映射  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
            
            for (int i = 0; i < parameterMappings.size(); i++) {
            	
                ParameterMapping parameterMapping = parameterMappings.get(i);
                
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                	
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry
                            .hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName
                            .startsWith(ForEachSqlNode.ITEM_PREFIX)
                            && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject
                                .getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value,parameterMapping.getJdbcType());
                }
            }
        }
    }


    private String generatePageSql(String sql, PageInfo page) {
        if (page != null && (dialect !=null || !dialect.equals(""))) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getCurrentResult() + ","
                        + page.getShowCount());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
                pageSql.append(sql);
                pageSql.append(")  tmp_tb where ROWNUM<=");
                pageSql.append(page.getCurrentResult() + page.getShowCount());
                pageSql.append(") where row_id>");
                pageSql.append(page.getCurrentResult());
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public Object plugin(Object arg0) {
        // TODO Auto-generated method stub
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (dialect ==null || dialect.equals("")) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if (dialect ==null || dialect.equals("")) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}