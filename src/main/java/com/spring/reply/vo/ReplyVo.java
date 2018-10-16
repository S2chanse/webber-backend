package com.spring.reply.vo;

public class ReplyVo {
	private String	reply_id;
	private String	board_id;
	private String  nickname;
	private String	content;
	private String  date;	
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public ReplyVo(String reply_id, String board_id, String nickname, String content, String date) {
		this.reply_id = reply_id;
		this.board_id = board_id;
		this.nickname = nickname;
		this.content = content;
		this.date = date;
	}
	@Override
	public String toString() {
		return "ReplyVo [reply_id=" + reply_id + ", board_id=" + board_id + ", nickname=" + nickname + ", content="
				+ content + ", date=" + date + "]";
	}
	
	
}
