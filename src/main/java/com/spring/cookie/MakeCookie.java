package com.spring.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

public class MakeCookie {
	public void makeCookie(HttpServletRequest req,HttpServletResponse resp,String token) {
		
			System.out.println("쿠키만들기 시작");
			Cookie cookie=new Cookie("auth",token);
			cookie.setMaxAge(60*60);
			resp.addCookie(cookie);
			System.out.println(cookie.getValue());
		
	}
}
