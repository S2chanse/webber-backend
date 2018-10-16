package com.spring.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadFile {
	public String makeFileName(String filePath,String testText, String string) {
		boolean isCheck=true;
		File file=null;
		 int i=0;
		 int strLen=filePath.lastIndexOf(".");
		 while(isCheck) {
				file 		 =new File(filePath);
				if(file.exists()) {
					i++;
					filePath=filePath.substring(0,strLen)+"("+String.valueOf(i)+")"+string;
					
				}else {
					isCheck=false;
				}
			}
		 //파일명 저장시 같은 파일이 존재하는가 확인 있으면 (i)가 붙는다.

		 FileOutputStream fos = null;
		 BufferedOutputStream bos = null;
		 //파일을 생성하고 저장한후, 그 파일 경로와 이름을 map에
		 //넣은 후 dao로 넘어간다.
		 try {
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos,1024);
			bos.write(testText.getBytes());
			bos.close();
			fos.close();
			System.out.println("저장됐습니다.");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		 
		return filePath;
	}
}
