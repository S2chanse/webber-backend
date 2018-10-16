package com.spring.template.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.error.NotExsistExcpetion;
import com.spring.reply.service.ReplyService;
import com.spring.reply.vo.ReplyVo;
import com.spring.result.vo.ResultMsgVo;
import com.spring.template.service.TemplateService;
import com.spring.template.vo.TemplateVo;

@RestController
@RequestMapping("/api/template")
public class TemplateRestController {
	@Autowired
	private TemplateService templateService;
	@Autowired
	private ReplyService replyService;
	
	//모든 템플릿 가져오기
	@RequestMapping(method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TemplateVo> getList(@RequestBody HashMap<String,Object> map){
		List<TemplateVo> templateList=templateService.getList(map);
		if(templateList.size()==0) {
			throw new NotExsistExcpetion("Not Exsist/-3");
			}
		return templateList;
	};
	
	//선택한 템플릿 가져오기
	@RequestMapping(value="/{board_id}",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public TemplateVo getTemplateVo(@PathVariable("board_id") String board_id ,@RequestBody HashMap<String,Object> map) {
		map.put("board_id", board_id);
		TemplateVo templateVo=templateService.getTemplateVo(map);
		if(templateVo==null) {
			throw new NotExsistExcpetion("Not Exsist/-3");
		}
		List<ReplyVo> replyList=replyService.getReplyList(map);
		templateVo.setReplies(replyList);
		return templateVo;
	}
	//탬플릿 생성하기
	@RequestMapping(method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody ResultMsgVo insertTemplate(@RequestBody HashMap<String,Object>map,HttpServletRequest req) {
		templateService.insertTemplate(map,req);
		System.out.println("임무완료:  "+map);
		if(map.get("err_code")!=null) {
			throw new NotExsistExcpetion("fail/-31");
		}
		//System.out.println("성공시 메세지:"+rmv.toString());
		return new ResultMsgVo();
	}
	//탬플릿 다운로드하기
	 @RequestMapping(value="/downloadFile/{type}/{userId}/{sfile:.+}",method=RequestMethod.GET)
	 public void pdsFileDownload(HttpServletResponse resp
			 	,@PathVariable("type") String type,@PathVariable("userId") String userId,@PathVariable("sfile") String sfile) throws IOException 
	 {		 
		  System.out.println(sfile);
		  
		 String INTERNAL_FILE		= sfile;
		 String EXTERNAL_FILE_PATH	="d:\\uploadFile\\"+userId+"\\"+sfile;
		 System.out.println(EXTERNAL_FILE_PATH);
		 File file=null;
		 if(type.equalsIgnoreCase("internal")) {//위의 mapping에서 type에 있는 값을 통해 서
			 ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
			 file=new File(ClassLoader.getSystemResource(INTERNAL_FILE).getFile());
		 }else {
			 //웹경로 알아내기
			 file=new File(EXTERNAL_FILE_PATH);
			 
		 }
			 if(!file.exists()) {
				 String  errorMSG	="Sorry, this file does not exist";
				 System.out.println(errorMSG);
				 OutputStream outputStream	= resp.getOutputStream();
				 outputStream.write(errorMSG.getBytes(Charset.forName("UTF-8")));
				 outputStream.close();
				 return;
				 
			 }
		 
		 
		 String mimeType =URLConnection.guessContentTypeFromName(file.getName());
		/* if(mimeType==null) {
			 System.out.println("mimeType is not detectable, will take default");
		 }*/
		 mimeType="application/octet-stream";
		 System.out.println("mimeType"+mimeType);
		 
		 resp.setContentType(mimeType);
		 resp.setHeader("Content-Dispostition"
				 ,String.format("inline;filename=\""+file.getName()+"\""));//파일주소
		 
		 resp.setContentLength((int)file.length());//용량전달
		 
		 InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		 
		 //Copy bytes from source to destination(outputStream)
		 FileCopyUtils.copy(inputStream, resp.getOutputStream());
		 
		 
	 }
	 
	 //템플릿 수정하기
	 @RequestMapping(value="/update",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	 public ResultMsgVo updateTemplate(@RequestParam HashMap<String,Object> map,HttpServletRequest req) {
		 map.put("htmlFilePath", "D:\\uploadFile\\C.m.A\\C.m.A(4).html");
		 map.put("cssFilePath", "D:\\uploadFile\\C.m.A\\C.m.A(4).css");
		 map.put("thumbnail", "/tImg/templatephoto/C.m.A/Jellyfish.jpg");
		 templateService.updateTemplate(map,req);
		 if(map.get("err_code")!=null) {
				throw new NotExsistExcpetion("fail/-32");
			}
		 return new ResultMsgVo();
		 
	 }
	 //템플릿 삭제하기
	 @RequestMapping(method=RequestMethod.DELETE,produces = "application/json; charset=utf-8")
	 public ResultMsgVo deleteTemplate(@RequestBody HashMap<String,Object>map) {
		 templateService.deleteTemplate(map);
		 if(map.get("err_code")!=null) {
				throw new NotExsistExcpetion("fail/-33");
			}
		 return new ResultMsgVo();
	 }
	 
	//검색한 템플릿 가져오기
	@RequestMapping(value="/search/{field}/{word}",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	public List<TemplateVo> getList(@PathVariable("field")String field,@PathVariable("word") String word,@RequestBody HashMap<String,Object> map){
		map.put("field", field);
		map.put("word", word);
		List<TemplateVo> templateList=templateService.getList(map);
		if(templateList.size()==0) {
			throw new NotExsistExcpetion("Not Exsist/-3");
			}
		return templateList;
	};
	
	//템플릿 좋아요 버튼 추가 삭제 둘다 가능
	@RequestMapping(value="/like",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResultMsgVo inDelLike(@RequestBody HashMap<String,Object> map) {
		templateService.inDelLike(map);
		return new ResultMsgVo();
	}
	//템플릿 즐찾 버튼 추가 삭제 둘다 가능
	@RequestMapping(value="/favor",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResultMsgVo inDelFavor(@RequestBody HashMap<String,Object> map) {
		templateService.inDelFavor(map);
		return new ResultMsgVo();
	}
	
}
