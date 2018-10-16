package com.spring.reply.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.reply.dao.ReplyDao;
import com.spring.reply.vo.ReplyVo;

@Repository("replyService")
public class ReplyServiceImpl implements com.spring.reply.service.ReplyService {
	@Autowired
	private ReplyDao replyDao;
	@Override
	public List<ReplyVo> getReplyList(HashMap<String, Object> map) {
		List<ReplyVo> replyList=replyDao.getReplyList(map);
		return replyList;
	}
	@Override
	public ReplyVo getReplyVo(HashMap<String, Object> map) {
		List<ReplyVo> replyList=replyDao.getReplyVoList(map);
		if(replyList.size()==0) {
			return null;
		}
		return replyList.get(0);
	}
	@Override
	public void insertReply(HashMap<String, Object> map) {
		replyDao.insertReply(map);
		
	}
	@Override
	public void updateReply(HashMap<String, Object> map) {
		replyDao.updateReply(map);
		
	}
	@Override
	public void deleteReply(HashMap<String, Object> map) {
		replyDao.deleteReply(map);
	}

}
