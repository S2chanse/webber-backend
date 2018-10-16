package com.spring.template.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.template.vo.TemplateVo;

public interface TemplateDao {

	List<TemplateVo> getList(HashMap<String, Object> map);

	List<TemplateVo> getTemplateVoList(HashMap<String, Object> map);

	void insertTemplate(HashMap<String, Object> map);

	void deleteTemplate(HashMap<String, Object> map);

	void updateTemplate(HashMap<String, Object> map);

	void indelLike(HashMap<String, Object> map);

	void indelFavor(HashMap<String, Object> map);

}
