package com.spring.session;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

public class MakeSession {
	
	public void makeSession(Map<String,Object> map,HttpServletRequest req,HttpServletResponse resp) {
		HttpSession session = null;
		String nickname=(String) map.get("nickname");
		String auth=(String) map.get("auth");
		String thumbnail=(String) map.get("thumbnail");
		session.setAttribute("nickname", nickname);
		session.setAttribute("thumnail", thumbnail);
		session.setAttribute("auth", auth);
	}
}
