package com.spring.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spring.cookie.MakeCookie;
import com.spring.token.service.TokenService;
import com.spring.user.vo.UserVo;



public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		Cookie cookie =WebUtils.getCookie(req, "auth");
		HttpSession session=req.getSession();
		if(session.getAttribute("nickname")==null) {
			
			if(cookie!=null) {
				String token=cookie.getValue();
				if(tokenService.isUsable(token)) {
					//토큰 해석			
					Map<String,Object> map=tokenService.get(token);
	
					String nickname=(String) map.get("nickname");
					String thumbnail=(String)map.get("thumbnail");
					int auth=(int) map.get("auth");
					
				
					//시간 비교
					Calendar nowTime=Calendar.getInstance();
					nowTime.setTime(new Date());
					Calendar exp=tokenService.getExp(token);
					if((exp.getTimeInMillis()-nowTime.getTimeInMillis())<253429974) {//만료일이 3일 이내면 새로 발급받음;
						UserVo userVo=new UserVo();
						userVo.setAuth(auth);
						userVo.setNickname(nickname);
						userVo.setThumbnail(thumbnail);
						token=tokenService.createToken(userVo);
						MakeCookie mc=new MakeCookie();
						mc.makeCookie(req, resp, token);
						System.out.println("새로운 쿠키를 굽어라");
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {				
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

}
