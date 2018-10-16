package com.spring.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class ImgFileModify {
	public void uploadImg(HttpServletRequest req, String filePathImg) {
		File file=null;
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) req;
		Iterator<String> iterator=mhsr.getFileNames();//Iterator 갯수 체크
		MultipartFile mf=null; 
				
		while (iterator.hasNext()) {
			mf=mhsr.getFile(iterator.next());
			HashMap<String,Object> hashmap=new HashMap();
			if(!mf.isEmpty()) {				
						file=new File(filePathImg);
					
						try {
							mf.transferTo(file);//실제파일명으로 저장
							
						}catch(IOException e) {
							System.out.println("오류: "+e.getMessage());
							e.printStackTrace();
						}
					}
					
				}
	
	}
}
