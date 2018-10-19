package com.spring.reply.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.error.NotExsistExcpetion;
import com.spring.error.UnAuthException;
import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVo;
import com.spring.result.vo.ResultMsgVo;

@RestController
@RequestMapping("/api/reply")
public class ReplyRestController {
	@Autowired
	private ReplyService replyService;
	
	//선택한 댓글을 가져온다.
	@RequestMapping(value="/{board_id}/{reply_id}",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReplyVo getReplyVo(@PathVariable("board_id") String board_id,@PathVariable("reply_id") String reply_id,@RequestBody HashMap<String,Object>map) {
		map.put("board_id", board_id);
		map.put("reply_id", reply_id);
		ReplyVo replyVo=replyService.getReplyVo(map);
		if(replyVo==null) {
			throw new NotExsistExcpetion("Not Exist/-7");
		}
		return replyVo;
	}
	
	//댓글을 생성한다.
	@RequestMapping(method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public ResultMsgVo insertReply(@RequestBody HashMap<String,Object>map) {
		replyService.insertReply(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-47474447")) {
				throw new UnAuthException("UnAuth/-301");				
			}
			throw new NotExsistExcpetion("fail/-41");
		}
		return new ResultMsgVo();
	}
	
	//댓글을 수정한다.
	@RequestMapping(method=RequestMethod.PUT,produces = "application/json; charset=utf-8")
	public ResultMsgVo modifyReply(@RequestBody HashMap<String,Object>map) {
		replyService.updateReply(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-47474447")) {
				throw new UnAuthException("UnAuth/-301");				
			}
					throw new NotExsistExcpetion("fail/-42");
		}
		return new ResultMsgVo();
	}
	
	//댓글을 삭제한다.
	@RequestMapping(method=RequestMethod.DELETE,produces = "application/json; charset=utf-8")
	public ResultMsgVo deleteReply(@RequestBody HashMap<String,Object>map) {
		replyService.deleteReply(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-47474447")) {
				throw new UnAuthException("UnAuth/-301");				
			}
			if(map.get("err_code").equals("-2747")) {
				throw new NotExsistExcpetion("fail/-301");
			}else {				
				throw new NotExsistExcpetion("fail/-43");
			}
		}
		return new ResultMsgVo();
	}
	
	
}
