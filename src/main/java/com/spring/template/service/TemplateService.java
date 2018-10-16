package com.spring.template.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.template.vo.TemplateVo;

public interface TemplateService {

	List<TemplateVo> getList(HashMap<String, Object> map);

	TemplateVo getTemplateVo(HashMap<String, Object> map);

	void insertTemplate(HashMap<String, Object> map, HttpServletRequest req);
	
	void updateTemplate(HashMap<String, Object> map, HttpServletRequest req);

	void deleteTemplate(HashMap<String, Object> map);

	void inDelLike(HashMap<String, Object> map);

	void inDelFavor(HashMap<String, Object> map);
}
