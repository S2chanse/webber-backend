package com.spring.result.vo;

import com.spring.user.vo.UserVo;

public class ResultLogInVo {
	private String token;
	private UserVo userVo;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public ResultLogInVo(String token, UserVo userVo) {
		this.token = token;
		this.userVo = userVo;
	}
	public ResultLogInVo() {
		
	}
	@Override
	public String toString() {
		return "ResultLogInVo [token=" + token + ", userVo=" + userVo + "]";
	}
	
}
