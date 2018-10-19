package com.spring.user.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.spring.cookie.MakeCookie;
import com.spring.error.NotExsistExcpetion;
import com.spring.error.OracleError;
import com.spring.file.UploadImgFile;
import com.spring.session.MakeSession;
import com.spring.token.service.TokenService;
import com.spring.user.dao.UserDao;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private TokenService tokenService;
	
	
	
	@Override
	public UserVo checkUser(HttpServletRequest req,HttpServletResponse resp,@RequestParam HashMap<String, Object> map) {	
				UserVo uservo=userDao.checkUser(map);
				if(uservo!=null) {
				System.out.println("fdsadfasdfa "+uservo);
				UserVo userVo=new UserVo();
				userVo.setAuth(uservo.getAuth());
				userVo.setNickname(uservo.getNickname());
				userVo.setThumbnail(uservo.getThumbnail());
				userVo.setSocialType(uservo.getSocialType());
				String token=tokenService.createToken(userVo);
				map.put("token", token);
				return userVo;
				}
		return uservo;
		
	}

	@Override
	public void insertUser(HashMap<String, Object> map) {
		userDao.insertUser(map);
		
	}

	@Override
	public List<UserVo> getList(HashMap<String, Object> map) {
		//System.out.println("중간지점");
		List<UserVo> userList=userDao.getList(map);
		return userList;
	}

	@Override
	public UserVo getUser(HashMap<String, Object> map) {
		List<UserVo> userList=userDao.getUser(map);
		if(userList.size()==0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public void updateInfo(HashMap<String, Object> map, HttpServletRequest req,HttpServletResponse resp) {
		UploadImgFile uif=new UploadImgFile();
		
		String nickName=(String) map.get("nickname");
		
		String filePath="D:\\wsspring02\\WebberPrj\\wepapp\\WEB-INF\\resources\\img\\profileP\\";
		filePath+=nickName;
		
	
		File file=new File(filePath);
		String sFileName="";	
			
			if(file.exists()) {

					if(file.isDirectory()){ //파일이 디렉토리인지 확인
		                 
			                File[] files = file.listFiles();
			                 
			                for( int i=0; i<files.length; i++){
			                    if( files[i].delete() ){
			                        System.out.println(files[i].getName()+" 삭제성공");
			                    }else{
			                        System.out.println(files[i].getName()+" 삭제실패");
			                    }
			                }
			                
					}
					
						sFileName=uif.uploadImg(req, filePath);				
						System.out.println("파일 생성");					
					
			}else {
				file.mkdirs();
				sFileName=uif.uploadImg(req, filePath);					
			}

	    if(sFileName!=null) {
		map.put("thumbnail", "/img/profileP/"+nickName+"/"+sFileName);
			
			userDao.updateInfo(map);
		
			List<UserVo> userList=userDao.getUser(map);
			UserVo uservo=userList.get(0);
			System.out.println(uservo);
			String token=tokenService.createToken( uservo);
			map.put("token", token);
			UserVo userVo=new UserVo();
			userVo.setAuth(uservo.getAuth());
			userVo.setNickname(uservo.getNickname());
			userVo.setThumbnail(uservo.getThumbnail());
			userVo.setSocialType(uservo.getSocialType());
			map.put("userVo", userVo);
		
	    }else {
	    	userDao.updateInfo(map);
	    }
	}

	@Override
	public void goneUser(HashMap<String, Object> map) {
		userDao.goneUser(map);
		
	}

	@Override
	public void checkUserNickName(HashMap<String, Object> map) {
		userDao.checkUserNickName(map);
		
	}

	



}
