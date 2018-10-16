package com.spring.reply.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.reply.dao.ReplyDao;
import com.spring.reply.vo.ReplyVo;
@Repository("replyDao")
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<ReplyVo> getReplyList(HashMap<String, Object> map) {
		sqlSession.selectList("Reply.ReplyList",map);
		return (List<ReplyVo>)map.get("result");
	}
	@Override
	public List<ReplyVo> getReplyVoList(HashMap<String, Object> map) {
		sqlSession.selectList("Reply.ReplyVo",map);
		List<ReplyVo> replyList=(List<ReplyVo>) map.get("result");
		return replyList;
	}
	@Override
	public void insertReply(HashMap<String, Object> map) {
		sqlSession.insert("Reply.ReplyInsert",map);	
	}
	@Override
	public void updateReply(HashMap<String, Object> map) {
		sqlSession.update("Reply.ReplyUpdate",map);
	}
	@Override
	public void deleteReply(HashMap<String, Object> map) {
		sqlSession.delete("Reply.ReplyDelete",map);
	}

}
