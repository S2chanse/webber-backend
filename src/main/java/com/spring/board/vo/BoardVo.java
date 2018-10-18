package com.spring.board.vo;

import java.util.List;

import com.spring.reply.vo.ReplyVo;

public class BoardVo {
	//fields
	private String board_Id;	
	private String nickname;	
	private String title;
	private String content;
	private int    views;
	private String regdate;
	private int		replyNum;
	private int		type;
	private String thumbnail;
	private List<ReplyVo> replies;
	//getter&setter
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getBoard_Id() {
		return board_Id;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<ReplyVo> getReplies() {
		return replies;
	}
	public void setReplies(List<ReplyVo> replies) {
		this.replies = replies;
	}
	public void setBoard_Id(String board_Id) {
		this.board_Id = board_Id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	//constructor
	public BoardVo() {}
	public BoardVo(String board_Id, String nickname, String title, String content, int views, String regdate,
			List<ReplyVo> replies) {
		this.board_Id = board_Id;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.views = views;
		this.regdate = regdate;
		this.replies = replies;
	}
	
	public BoardVo(String board_Id, String nickname, String title, String content, int views, String regdate,
			int replyNum, int type, List<ReplyVo> replies) {
		super();
		this.board_Id = board_Id;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.views = views;
		this.regdate = regdate;
		this.replyNum = replyNum;
		this.type = type;
		this.replies = replies;
	}
	
	public BoardVo(String board_Id, String nickname, String title, String content, int views, String regdate,
			int replyNum, int type, String thumbnail, List<ReplyVo> replies) {
		this.board_Id = board_Id;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.views = views;
		this.regdate = regdate;
		this.replyNum = replyNum;
		this.type = type;
		this.thumbnail = thumbnail;
		this.replies = replies;
	}
	//toString
	@Override
	public String toString() {
		return "BoardVo [board_Id=" + board_Id + ", nickname=" + nickname + ", title=" + title + ", content=" + content
				+ ", views=" + views + ", regdate=" + regdate + "]";
	}
	
	
}
