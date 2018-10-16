package com.spring.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.error.NotExsistExcpetion;
import com.spring.result.vo.ResultMsgVo;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	//전체 유저 리스트
	@RequestMapping(method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public List<UserVo> getList(@RequestBody HashMap<String,Object> map){
		//System.out.println("유저 리스트 시작");
		List<UserVo> userList=userService.getList(map);
		if(userList.size()==0) {
			throw new NotExsistExcpetion("Not Exsist/-1");
		}
		return userList;
	}
	//선택한 유저
	@RequestMapping(value="/{nickname}",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public UserVo getUser(@PathVariable("nickname") String nickname,@RequestBody HashMap<String,Object> map) {
		map.put("nickname", nickname);
		UserVo user=userService.getUser(map);
		if(user==null) {
			throw new NotExsistExcpetion("Not Exsist/-1");
		}
		return user;
	}
	//유저 정보수정
	@RequestMapping(value="/{nickname}",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public ResultMsgVo updateProfile(@PathVariable("nickname") String nickname,@RequestParam HashMap<String,Object>map,HttpServletRequest req,HttpServletResponse resp) {
		map.put("nickname", nickname);
		userService.updateInfo(map,req,resp);
		if(map.get("err_code")!=null) {
			throw new NotExsistExcpetion("fail/-13");
		}
		return new ResultMsgVo();
	}
	//탈퇴
	@RequestMapping(value="/{nickname}",method=RequestMethod.DELETE,produces = "application/json; charset=utf-8")
	public ResultMsgVo goneUser(@PathVariable("nickname") String nickname,@RequestBody HashMap<String,Object> map){
		map.put("nickname", nickname);
		map.put("type", 1);
		userService.goneUser(map);
		if(map.get("err_code")!=null) {
			throw new NotExsistExcpetion("fail/-14");
		}
		return new ResultMsgVo();
	}
	//강제 추방
	@RequestMapping(value="/admin/{nickname}",method=RequestMethod.DELETE,produces = "application/json; charset=utf-8")
	public ResultMsgVo outUser(@PathVariable("nickname") String nickname,@RequestBody HashMap<String,Object> map){
		map.put("nickname", nickname);
		map.put("type", 2);
		userService.goneUser(map);
		if(map.get("err_code")!=null) {
			throw new NotExsistExcpetion("fail/-14");
		}
		return new ResultMsgVo();
	}
	//검색한 유저들
	@RequestMapping(value="/search",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public List<UserVo> getSearchList(@RequestParam HashMap<String,Object> map){
		List<UserVo> userList=userService.getList(map);
		if(userList.size()==0) {
			throw new NotExsistExcpetion("Not Exsist/-1");
		}
		return userList;
	}
	
	
}
