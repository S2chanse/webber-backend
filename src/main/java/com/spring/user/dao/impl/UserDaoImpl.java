package com.spring.user.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.user.dao.UserDao;
import com.spring.user.vo.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserVo checkUser(@RequestParam HashMap<String, Object> map) {
		sqlSession.selectList("User.UserCheck", map);
		//System.out.println("mapper에서 갓나온 map"+map);
		List<UserVo>userList=(List<UserVo>) map.get("result");
		if(userList==null) {
			return null;
		}
		return userList.get(0);
		
	}
	@Override
	public void insertUser(HashMap<String, Object> map) {
		sqlSession.insert("User.UserInsert",map);
		
	}
	@Override
	public List<UserVo> getList(HashMap<String, Object> map) {
		//System.out.println("유저리스트 다오");
		sqlSession.selectList("User.UserList",map);
		return (List<UserVo>)map.get("result");
	}
	@Override
	public List<UserVo> getUser(HashMap<String, Object> map) {
		sqlSession.selectList("User.UserVo",map);
		return (List<UserVo>)map.get("result");
	}
	@Override
	public void updateInfo(HashMap<String, Object> map) {
		sqlSession.update("User.UserUpdate",map);
		
	}
	@Override
	public void goneUser(HashMap<String, Object> map) {
		sqlSession.delete("User.UserGone",map);
		
	}
	@Override
	public void checkUserNickName(HashMap<String, Object> map) {
		sqlSession.selectList("User.UserNicknameCheck", map);
		
	}


}
