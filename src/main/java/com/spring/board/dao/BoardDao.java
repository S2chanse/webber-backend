package com.spring.board.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.board.vo.BoardVo;

public interface BoardDao {
	public List<BoardVo> getList(HashMap<String,Object> map);

	public List<BoardVo> getBoardVo(HashMap<String, Object> map);

	public void insertBoard(HashMap<String, Object> map);

	public void updateBoard(HashMap<String, Object> map);

	public void deleteBoard(HashMap<String, Object> map);
}
