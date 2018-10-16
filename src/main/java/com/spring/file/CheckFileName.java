package com.spring.file;

import java.io.File;

public class CheckFileName {

	public String getCheckFileName(String filePath, String orgFileName, String fileExt) {
		boolean isCheck=true;
		String returnFileName	=null;
		
		String fullFilePath		=null;
		File file		   		=null;
		
		int fileLen=orgFileName.length();
		int i=0;
		while(isCheck) {
			fullFilePath =filePath+orgFileName+fileExt;
			file 		 =new File(fullFilePath);
			if(file.exists()) {
				i++;
				orgFileName= orgFileName.substring(0, fileLen)+"("+String.valueOf(i)+")";
			}else {
				isCheck=false;
			}
		}
		return orgFileName+fileExt;
	}
}


