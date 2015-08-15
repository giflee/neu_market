package com.tarena.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.client.HttpServerErrorException;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String[] notFilter =  
	            new String[] {"/images", "/js", "/css","admin_login.html","login.do","/stage","/pre","/font","/webinfo/singleFindAll.do"};
		 String uri = request.getRequestURI();  
	        // 是否过滤  
	        boolean doFilter = true;  
	        for (String s : notFilter)  
	        {  
	            if (uri.indexOf(s) != -1)  
	            {  
	                doFilter = false;  
	                break;  
	            }  
	        }
	        String admin = (String) request.getSession().getAttribute("admin");
	        if(doFilter){
	        	if(admin==null||admin.equals("")){
	        		response.sendRedirect("/Shop/admin_login.html");
	        	}else{
	        		chain.doFilter(request, response);
	        	}
	        }else{
	        	chain.doFilter(request, response);
	        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
