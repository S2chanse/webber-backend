package com.spring.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadImgFile {


	public String uploadImg(HttpServletRequest req, String filePathImg) {
		File file=null;
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) req;
		Iterator<String> iterator=mhsr.getFileNames();//Iterator 갯수 체크
		MultipartFile mf=null; 
		
	    CheckFileName checkFile=new CheckFileName();
	    		
	    String fileName	   ="";
		String orgFileName ="";
		String fileExt	   ="";
		String sFileName   ="";
		int i=0;
				
		while (iterator.hasNext()) {
			mf=mhsr.getFile(iterator.next());
			HashMap<String,Object> hashmap=new HashMap();
			if(!mf.isEmpty()) {
				fileName=mf.getOriginalFilename();
				orgFileName=fileName.substring(0, fileName.lastIndexOf("."));
						
				fileExt=fileName.substring(fileName.lastIndexOf("."));
				if(fileExt.equals("jpg")||fileExt.equals("png")) {
					return null;
				}
				//중복파일 존재하면 번호추가 후 실제 파일명 처리;
				sFileName=checkFile.getCheckFileName(filePathImg,orgFileName,fileExt);
						
						
						file=new File(filePathImg+"\\"+sFileName);
						i+=1;
						try {
							mf.transferTo(file);//실제파일명으로 저장
							
						}catch(IOException e) {
							System.out.println("오류: "+e.getMessage());
							e.printStackTrace();
						}
					}
					
				}
		return sFileName;
	}
}
