package com.spring.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVo;
import com.spring.token.service.TokenService;
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private TokenService tokenService;
	
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
		String token=(String) map.get("access_token");
		if(tokenService.isUsable(token)) {
			Map<String,Object> userInfo=tokenService.get(token);
			String nickname=(String) userInfo.get("nickname");
			map.put("nickname",nickname);
			boardDao.insertBoard(map);
		}else {
			map.put("err_code", "-47474447");
			return ;
		}
		
	}
	@Override
	public void updateBoard(HashMap<String, Object> map) {
		String token=(String) map.get("access_token");
		if(tokenService.isUsable(token)) {
			Map<String,Object> userInfo=tokenService.get(token);
			String nickname=(String) userInfo.get("nickname");
			map.put("nickname",nickname);
			boardDao.updateBoard(map);
		}else {
			map.put("err_code", "-47474447");
			return ;
		}
	   
	}
	
	@Override
	public void deleteBoard(HashMap<String, Object> map) {
		boardDao.deleteBoard(map);
		
	}

}
