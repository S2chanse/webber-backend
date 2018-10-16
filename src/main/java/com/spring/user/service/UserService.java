package com.spring.user.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import com.spring.user.vo.UserVo;

public interface UserService {

	public UserVo checkUser(HttpServletRequest req,HttpServletResponse resp,@RequestParam HashMap<String, Object> map);

	public void insertUser(HashMap<String, Object> map);

	public List<UserVo> getList(HashMap<String, Object> map);

	public UserVo getUser(HashMap<String, Object> map);

	public void updateInfo(HashMap<String, Object> map, HttpServletRequest req,HttpServletResponse resp);

	public void goneUser(HashMap<String, Object> map);

	public void checkUserNickName(HashMap<String, Object> map);

}
