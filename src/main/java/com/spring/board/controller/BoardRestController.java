package com.spring.board.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.error.NotExsistExcpetion;
import com.spring.error.UnAuthException;
import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVo;
import com.spring.result.vo.ResultMsgVo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/community")
public class BoardRestController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	//전체 게시물 가져오기
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<BoardVo> getList(HashMap<String,Object>map){
		List<BoardVo> boardList=boardService.getList(map);
		return boardList;
	}
	//선택한 게시물 및 댓글 리스트 가져오기
	@RequestMapping(value="/{board_id}",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public BoardVo getBoardVo(		 
			 @PathVariable("board_id") String board_id,@RequestBody HashMap<String,Object>map){
		map.put("board_id", board_id);
		BoardVo board=boardService.getBoardVo(map);
		if(board==null) {
			throw new NotExsistExcpetion("No data Found/-5");
		}
		List<ReplyVo>replyList=replyService.getReplyList(map);
		board.setReplies(replyList);
/*		System.out.println(replyList.toString());
		System.out.println(map);
		System.out.println(board);*/
		return board;
	}
	
	/*POST	/api/community  {nickname, title, contents}	게시판에 자료를 추가한다.
	PUT	/api/community/:board_id {title, contents}	게시판에 자료를 수정한다.
	DELETE	/api/community/:board_id	구분자 값의 게시자료를 삭제한다.*/
	
	//자유게시판 게시물 추가하기
	@RequestMapping(method=RequestMethod.POST)
	public ResultMsgVo insertBoard(@RequestParam HashMap<String,Object> map) {
		System.out.println("fdsfasdf "+map);
		boardService.insertBoard(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-47474447")) {
				throw new UnAuthException("UnAuth/-301");				
			}
			throw new NotExsistExcpetion("fail/-21");
		}
		return new ResultMsgVo();
	}
	
	//자유게시판 게시물 수정하기
	@RequestMapping(value="/{board_id}",method=RequestMethod.PUT,produces="application/json;charset=utf-8")
	public ResultMsgVo updateBoard(@PathVariable("board_id")String board_id,@RequestParam HashMap<String,Object>map) {
		map.put("board_id", board_id);
		boardService.updateBoard(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-47474447")) {
				throw new UnAuthException("UnAuth/-301");				
			}
			throw new NotExsistExcpetion("fail/-22");
		}
		return new ResultMsgVo();
	}
	
	//자유게시판 게시물 삭제하기
	@RequestMapping(value="/{board_id}",method=RequestMethod.DELETE,produces="application/json;charset=utf-8")
	public ResultMsgVo deleteBoard(@PathVariable("board_id")String board_id,@RequestBody HashMap<String,Object>map) {
		map.put("board_id", board_id);
		boardService.deleteBoard(map);
		if(map.get("err_code")!=null) {
			if(map.get("err_code").equals("-2747")) {
				throw new NotExsistExcpetion("fail/-301");				
			}else {				
				throw new NotExsistExcpetion("fail/-23");
			}
		}
		return new ResultMsgVo();
	}
	//검색한 게시물만 가져오기
	@RequestMapping(value="/search/{field}/{word}",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<BoardVo> getSearchList(@PathVariable("field") String field,@PathVariable("word") String word,
			@RequestBody HashMap<String,Object>map){
			map.put("field", field);
			map.put("word", word);
			List<BoardVo> boardList=boardService.getList(map);
			if(boardList.size()==0) {
				throw new NotExsistExcpetion("No data Found/-300");
			}
		return boardList;
	}
}
