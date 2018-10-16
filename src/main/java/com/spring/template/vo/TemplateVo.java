package com.spring.template.vo;

import java.util.List;

import com.spring.reply.vo.ReplyVo;

public class TemplateVo {
	private String template_id;
	private String nickname;
	private String file_name;
	private String file_path_html;
	private String file_path_css;
	private String thumbnail;
	private String htmlContent;
	private String cssContent;
	private int views;
	private int likes;
	private String regdate;
	private int favor;
	private List<ReplyVo> replies;

	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getCssContent() {
		return cssContent;
	}
	public void setCssContent(String cssContent) {
		this.cssContent = cssContent;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path_html() {
		return file_path_html;
	}
	public void setFile_path_html(String file_path_html) {
		this.file_path_html = file_path_html;
	}
	public String getFile_path_css() {
		return file_path_css;
	}
	public void setFile_path_css(String file_path_css) {
		this.file_path_css = file_path_css;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getFavor() {
		return favor;
	}
	public void setFavor(int favor) {
		this.favor = favor;
	}
	public List<ReplyVo> getReplies() {
		return replies;
	}
	public void setReplies(List<ReplyVo> replies) {
		this.replies = replies;
	}
	
	public TemplateVo() {
	}
	public TemplateVo(String template_id, String nickname, String file_name, String file_path_html,
			String file_path_css, String thumbnail, int views, int likes, String regdate, int favor,
			List<ReplyVo> replies) {
		this.template_id = template_id;
		this.nickname = nickname;
		this.file_name = file_name;
		this.file_path_html = file_path_html;
		this.file_path_css = file_path_css;
		this.thumbnail = thumbnail;
		this.views = views;
		this.likes = likes;
		this.regdate = regdate;
		this.favor = favor;
		this.replies = replies;
	}

}
