package com.spring.template.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.file.CheckFileName;
import com.spring.file.ImgFileModify;
import com.spring.file.ReadFile;
import com.spring.file.UploadFile;
import com.spring.file.UploadImgFile;
import com.spring.template.dao.TemplateDao;
import com.spring.template.service.TemplateService;
import com.spring.template.vo.TemplateVo;
import com.spring.token.service.TokenService;
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateDao templateDao; 
	@Autowired
	private TokenService tokenService;
	@Override
	public List<TemplateVo> getList(HashMap<String, Object> map) {
		List<TemplateVo> templateList=templateDao.getList(map);
		return templateList;
	}
	@Override
	public TemplateVo getTemplateVo(HashMap<String, Object> map) {
		List<TemplateVo> templateList=templateDao.getTemplateVoList(map);
		TemplateVo tVo=templateList.get(0);
		ReadFile rf=new ReadFile();
		//Html내용 갖고와서 Vo에 넣음
		String htmlContents=rf.readFile(tVo.getFile_path_html());
		tVo.setHtmlContent(htmlContents);
		//Css 내용 갖고와서 Vo에 넣음
		String cssContents=rf.readFile(tVo.getFile_path_css());
		tVo.setCssContent(cssContents);
		if(templateList.size()==0) {
			return null;
		}
		return tVo;
	}
	@Override
	public void insertTemplate(HashMap<String, Object> map,HttpServletRequest req) {
		System.out.println("자료-------------"+map);
		System.out.println("req-------------"+req);
		String token=(String) map.get("access_token");
		if(tokenService.isUsable(token)) {
			Map<String,Object> userInfo=tokenService.get(token);
			String nickname=(String) userInfo.get("nickname");
			map.put("nickname",nickname);
		}else {
			map.put("err_code", "-47474447");
			return ;
		}
		
		
		
		//업로드될 파일명과 같은 파일이 있는지 확인		
		/*
		 * 파일 업로드 비지니스 로직 시작
		 * 1. 입력받은 문자들은 Boarder 테이블에 저장
		 * 
		 * 2.(MultipartHttpServletRequest 객체가 담당)
		 * file들을 disk(upload파일)에 저장
		 * 
		 * 3.DaoImple을 통해 업로드된 파일정보들을 Files 테이블에 저장
		 * 
		 * */
		UploadFile	  ulf=new UploadFile();
		UploadImgFile uif=new UploadImgFile();
		
		String nickName=(String) map.get("nickname");
		
		String htmlCont=(String) map.get("htmlCont");
		 	
		
		String cssCont=(String) map.get("cssCont"); 
		
		
		//img파일 저장 위치
		String filePath="D:\\wsspring02\\WebberPrj\\wepapp\\WEB-INF\\resources\\img\\templateP\\";
			   filePath+=nickName;
			   
	    File file=new File(filePath);
	    if(!file.exists()) {
	    	file.mkdirs();
	    }
	    
	    System.out.println("파일 생성");
	    String sFileName=uif.uploadImg(req, filePath);
		map.put("thumbnail", "/img/templateP/"+nickName+"/"+sFileName);
		// /tImg/="d:\\uploadFile 경로"
		filePath ="D:\\uploadFile";
		
		file=new File(filePath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		filePath +="\\"+nickName;
		
		int filePathLen=filePath.length();
		
		file=new File(filePath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String filePathHtml=filePath+"\\"+nickName+".html";
		String filePathCss =filePath+"\\"+nickName+".css";
		String htmlPath=ulf.makeFileName(filePathHtml, htmlCont, ".html");		
		String cssPath=ulf.makeFileName(filePathCss, cssCont, ".css");		
		
		System.out.println("파일이름: "+filePathHtml.substring(filePathLen+1));
		map.put("fileName", htmlPath.substring(filePathLen+1));
		map.put("filePathHTML", htmlPath);
		map.put("filePathCSS", cssPath);
		
		//System.out.println("현재 만들어진 어쩌구 저쩌구"+map);
		templateDao.insertTemplate(map);
	}
	
	@Override
	public void updateTemplate(HashMap<String, Object> map,HttpServletRequest req) {
		
		String token=(String) map.get("token");
		
		if(tokenService.isUsable(token)) {
			Map<String,Object> userInfo=tokenService.get(token);
			String nickname=(String) userInfo.get("nickname");
			map.put("nickname",nickname);
		}else {
			map.put("err_code", "-47474447");
			return ;
		}
		String nickName=(String) map.get("nickname");
		
		File file=null;
		UploadFile	  ulf=new UploadFile();
		UploadImgFile uif=new UploadImgFile();
		String htmlPath=(String) map.get("htmlFilePath");
		String htmlContent=(String) map.get("htmlCont");
		
		String cssPath=(String) map.get("cssFilePath");
		String cssContent=(String) map.get("cssCont");
		
		file=new File(htmlPath);
			if(htmlContent.length()>0) {
			 FileOutputStream fos = null;
			 BufferedOutputStream bos = null;
			 //파일을 생성하고 저장한후, 그 파일 경로와 이름을 map에
			 //넣은 후 dao로 넘어간다.
			 try {
				fos=new FileOutputStream(file);
				bos=new BufferedOutputStream(fos,1024);
				bos.write(htmlContent.getBytes());
				bos.close();
				fos.close();
				System.out.println("저장됐습니다.");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			 
			}
			file=new File(cssPath);
			if(cssContent.length()>0) {
				FileOutputStream fos = null;
				BufferedOutputStream bos = null;
				//파일을 생성하고 저장한후, 그 파일 경로와 이름을 map에
				//넣은 후 dao로 넘어간다.
				try {
					fos=new FileOutputStream(file);
					bos=new BufferedOutputStream(fos,1024);
					bos.write(cssContent.getBytes());
					bos.close();
					fos.close();
					System.out.println("저장됐습니다.");
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
			String filePathImg=(String) map.get("thumbnail");
		
		
			filePathImg=filePathImg.substring(4);
			System.out.println("----------------"+filePathImg);
			ImgFileModify ifm=new ImgFileModify();
			ifm.uploadImg(req, "D:\\wsspring02\\WebberPrj\\wepapp\\WEB-INF\\resources\\img\\"+filePathImg);
	
		System.out.println("수정용"+map);
		
		
	}
	@Override
	public void deleteTemplate(HashMap<String, Object> map) {
		templateDao.deleteTemplate(map);
		
	}
	@Override
	public void inDelLike(HashMap<String, Object> map) {
		templateDao.indelLike(map);
		
	}
	@Override
	public void inDelFavor(HashMap<String, Object> map) {
		templateDao.indelFavor(map);
		
	}

}
