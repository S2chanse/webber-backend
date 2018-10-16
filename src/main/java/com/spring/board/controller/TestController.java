package com.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.error.NotExsistExcpetion;
import com.spring.error.NotFoundError;
import com.spring.error.OracleError;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@CrossOrigin("http://localhost:3000")
@Controller
public class TestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody UserVo home(@RequestBody HashMap<String,Object> data,HttpServletRequest req,HttpServletResponse resp,
			@RequestParam HashMap<String,Object> map) {
		
		
		System.out.println("[type]: "+ data.get("type"));
		System.out.println("[googleId]: "+ data.get("googleId"));
		System.out.println("[accessToken]: "+ data.get("accessToken"));
		System.out.println("[email]: "+ data.get("email"));
		System.out.println("[imageUrl]: "+ data.get("imageUrl"));
		
		String type=(String) data.get("type");
		String id=(String) data.get("googleId");
		map.put("type", type);
		map.put("Id",id);
		
		System.out.println("현재 map에 담긴 정보:  "+map);
		
		UserVo userVo=userService.checkUser(req, resp, map);
		
		
		return userVo;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
			
		return "home";
	}
	
	@RequestMapping("/notFound")
	public void noFoundError() {
		throw new NotFoundError("error/-7");
	}
}
