package com.edu.moocs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.moocs.moocs.jsf.AuthenticationBean;


@WebFilter("/pages/admin/resultat.jsf")
public class ResultatOfQuizSecurityFilter implements Filter {

    public ResultatOfQuizSecurityFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		AuthenticationBean authBean = (AuthenticationBean) req.getSession().getAttribute("authBean");
		
		boolean letGo = false;
		
		if ((authBean != null) && (authBean.getUserType().equals("student")))
				 	{
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
