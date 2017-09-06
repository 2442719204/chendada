package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharacterFilter implements Filter{
	FilterConfig config = null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String charset = config.getInitParameter("character");
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset); 
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		
	}

}
