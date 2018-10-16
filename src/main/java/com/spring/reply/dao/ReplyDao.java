package com.spring.reply.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.reply.vo.ReplyVo;

public interface ReplyDao {
	public List<ReplyVo> getReplyList(HashMap<String,Object> map);

	public List<ReplyVo> getReplyVoList(HashMap<String, Object> map);

	public void insertReply(HashMap<String, Object> map);

	public void updateReply(HashMap<String, Object> map);

	public void deleteReply(HashMap<String, Object> map);
}
