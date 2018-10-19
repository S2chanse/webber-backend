package com.spring.board.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<BoardVo> getList(HashMap<String, Object> map) {
		sqlSession.selectList("Board.BoardList",map);
		return ((List<BoardVo>)map.get("result"));
	}
	@Override
	public List<BoardVo> getBoardVo(HashMap<String, Object> map) {
		sqlSession.selectList("Board.BoardVo", map);
		List<BoardVo>boardList=(List<BoardVo>) map.get("result");

		return boardList;
	}
	@Override
	public void insertBoard(HashMap<String, Object> map) {
		sqlSession.insert("Board.BoardInsert",map);
		System.out.println("나온 맵"+map);
		
	}
	@Override
	public void updateBoard(HashMap<String, Object> map) {
		sqlSession.update("Board.BoardUpdate",map);
		
	}
	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		sqlSession.delete("Board.BoardDelete",map);
		
	}

}
