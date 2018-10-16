package com.spring.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.spring.template.vo.TemplateVo;

public class ReadFile {
	public String readFile(String addr) {
		String filename=addr;
		System.out.println("파일주소임"+filename);
		FileReader fr=null;
		BufferedReader br=null;
		String contents="";
		String Tages="";
		try {
			fr=new FileReader(filename);
			br=new BufferedReader(fr);
			while((Tages=br.readLine())!=null) {
				contents+=Tages;
			}
			System.out.println("내용물: "+contents);
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return contents;
		
	}
}
