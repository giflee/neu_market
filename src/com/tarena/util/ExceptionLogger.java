package com.tarena.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tarena.entity.Admin;
import com.tarena.entity.JsonWrapper;
import com.tarena.entity.User;
@Component
@Aspect
public class ExceptionLogger {
	@Around("within(com.tarena.controller.*)")
	public JSONObject log(ProceedingJoinPoint p){
		Object jo = null;
		try {
			jo=p.proceed();//调用目标组件
		} catch (Throwable e) {
			e.printStackTrace();
			//创建日志记录器
			Logger logger = Logger.getLogger(this.getClass());
			//记录日志
			ServletRequestAttributes sr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = sr.getRequest();
			HttpSession session = request.getSession();
			//获取参数
			String admin =  (String) session.getAttribute("admin");
			String ip = request.getRemoteHost();//IP地址
			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String className = p.getTarget().getClass().getName();
			String methodName = p.getSignature().getName();
			if(admin!=null){
				logger.error("用户["+admin+"],");
			}
			logger.error("IP["+ip+"],");
			logger.error("在["+now+"],");
			logger.error("调用方法["+className+"."+methodName+"]时,发生如下异常：\n");
			StackTraceElement[] eles = e.getStackTrace();
			for(StackTraceElement ex :eles){
				logger.error("\t"+ex.toString()+"\n");
			}
			//发生异常时不再返回目标组件的返回值 而是返回错误信息
			 return new JsonWrapper.Builder(false,null,"系统异常 ，请联系管理员",null).build();
		}
		JSONObject jo1= new JSONObject();
		jo1.put("key", jo);
		 return new JsonWrapper.Builder(true,null,null,jo1).build();
	}
}
