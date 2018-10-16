package com.spring.reply.service;

import java.util.HashMap;
import java.util.List;

import com.spring.reply.vo.ReplyVo;

public interface ReplyService {
	public List<ReplyVo> getReplyList(HashMap<String,Object> map);

	public ReplyVo getReplyVo(HashMap<String, Object> map);

	public void insertReply(HashMap<String, Object> map);

	public void updateReply(HashMap<String, Object> map);

	public void deleteReply(HashMap<String, Object> map);
}
