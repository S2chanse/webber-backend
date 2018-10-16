package com.spring.user.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.spring.user.vo.UserVo;

public interface UserDao {

	public UserVo checkUser(@RequestParam HashMap<String, Object> map);

	void insertUser(HashMap<String, Object> map);

	List<UserVo> getList(HashMap<String, Object> map);

	public List<UserVo> getUser(HashMap<String, Object> map);

	public void updateInfo(HashMap<String, Object> map);

	public void goneUser(HashMap<String, Object> map);

	public void checkUserNickName(HashMap<String, Object> map);

}
