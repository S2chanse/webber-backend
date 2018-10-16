package com.spring.login.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.spring.error.NotExsistExcpetion;
import com.spring.error.OracleError;
import com.spring.result.vo.ResultMsgVo;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{nickname}",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultMsgVo userNickNameCheck(@PathVariable("nickname")String nickname,@RequestBody HashMap<String,Object> map) {
		map.put("nickname", nickname);
		userService.checkUserNickName(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-12")) {
				throw new NotExsistExcpetion("exsist/-12");
			}
		}
		return new ResultMsgVo();
		
	}
	@RequestMapping(value="/login",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResultMsgVo userLogIn(@RequestBody HashMap<String,Object> map,HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("로그인시 들어오는 정보들"+map);
		userService.checkUser(req,resp,map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-4444")) {			
				throw new NotExsistExcpetion("Out User/-2");
			}
			if(map.get("err_code").equals("77777")) {			
				throw new NotExsistExcpetion("Not User/-1");
			}
				throw new OracleError("Not regist/-9");
		}
		return new ResultMsgVo();
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResultMsgVo userRegist(@RequestBody HashMap<String,Object> map) {
		System.out.println("회원가입"+map);
		
		userService.insertUser(map);
		return new ResultMsgVo();
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResultMsgVo userRegist(HttpServletRequest req,HttpServletResponse resp) {
		HttpSession session=req.getSession();
		session.invalidate();
		Cookie kc = new Cookie("auth", null); 
		// choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
		kc.setMaxAge(0); // 유효시간을 0으로 설정
		resp.addCookie(kc);
		
		return new ResultMsgVo();
	}
}
