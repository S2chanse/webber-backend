package com.spring.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVo;
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	@Override
	public List<BoardVo> getList(HashMap<String, Object> map) {
		List<BoardVo> boardList=boardDao.getList(map);
		return boardList;
	}
	@Override
	public BoardVo getBoardVo(HashMap<String, Object> map) {
		List<BoardVo> boardVo=boardDao.getBoardVo(map);
		if(boardVo.size()==0) {
			return null;
		}
		return boardVo.get(0);
	}
	@Override
	public void insertBoard(HashMap<String, Object> map) {
		boardDao.insertBoard(map);
		
	}
	@Override
	public void updateBoard(HashMap<String, Object> map) {
	    boardDao.updateBoard(map);
	}
	
	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		boardDao.deleteBoard(map);
		
	}

}
