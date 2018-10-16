package com.spring.token.service;

import java.util.Calendar;
import java.util.Map;

import com.spring.user.vo.UserVo;

public interface TokenService {

	public <T> String createToken(UserVo uservo);

	boolean isUsable(String token);

	Map<String, Object> get(String token);

	public Calendar getExp(String token);


}
