package com.edu.moocs.filter;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import com.edu.moocs.moocs.jsf.AuthenticationBean;

@WebFilter("/pages/homeTeacher.jsf")
public class TeacherSecurityFilter implements Filter {

    public TeacherSecurityFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		AuthenticationBean authBean = (AuthenticationBean) req.getSession().getAttribute("authBean");

		boolean letGo = false;
		
		if ((authBean != null) &&
				 	(authBean.getUserType().equals("teacher"))){
			letGo = true;
		}
		
		if (letGo) {
			chain.doFilter(request, response);
		}else{
			resp.sendRedirect(req.getContextPath()+ "/welcome.jsf");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}