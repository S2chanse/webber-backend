package com.spring.board.service;

import java.util.HashMap;
import java.util.List;

import com.spring.board.vo.BoardVo;

public interface BoardService {
	public List<BoardVo>getList(HashMap<String,Object> map);

	public BoardVo getBoardVo(HashMap<String, Object> map);

	public void insertBoard(HashMap<String, Object> map);

	public void updateBoard(HashMap<String, Object> map);

	public void deleteBoard(HashMap<String, Object> map);
}
