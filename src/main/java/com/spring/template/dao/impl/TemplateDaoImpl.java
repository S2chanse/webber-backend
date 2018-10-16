package com.spring.template.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.template.dao.TemplateDao;
import com.spring.template.vo.TemplateVo;

@Repository("templateDao")
public class TemplateDaoImpl implements TemplateDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<TemplateVo> getList(HashMap<String, Object> map) {
		sqlSession.selectList("Template.TemplateList",map);
		return (List<TemplateVo>)map.get("result");
	}
	
	@Override
	public List<TemplateVo> getTemplateVoList(HashMap<String, Object> map) {
		sqlSession.selectList("Template.TemplateVo",map);
		return (List<TemplateVo>)map.get("result");
	}
	
	@Override
	public void insertTemplate(HashMap<String, Object> map) {
		sqlSession.insert("Template.TemplateInsert",map);
		
	}
	
	@Override
	public void deleteTemplate(HashMap<String, Object> map) {
		sqlSession.delete("Template.TemplateDelete",map);
		
	}

	@Override
	public void updateTemplate(HashMap<String, Object> map) {
		sqlSession.update("Template.TemplateUpdate",map);
		
	}

	@Override
	public void indelLike(HashMap<String, Object> map) {
		sqlSession.insert("Template.TemplateLike",map);
	}
	@Override
	public void indelFavor(HashMap<String, Object> map) {
		sqlSession.insert("Template.TemplateFavor",map);
	}

}
