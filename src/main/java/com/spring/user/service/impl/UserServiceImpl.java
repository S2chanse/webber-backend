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
		UserVo userVo=new UserVo();
		Cookie cookie=WebUtils.getCookie(req, "auth");
		if(cookie!=null) {
			String cookieStr=cookie.getValue();
			Map<String,Object> tokenMap=tokenService.get(cookieStr);
			String nickname=(String) map.get("nickname");
			String thumbnail=(String)map.get("thumbnail");
			int auth=(int) map.get("auth");
			userVo.setAuth(auth);
			userVo.setNickname(nickname);
			userVo.setThumbnail(thumbnail);
			
		}else {	
			UserVo uservo=userDao.checkUser(map);
			//System.out.println("유저내용ㅣ  "+uservo.toString());
			if(uservo==null) {
				if(map.get("err_code")!=null) {
					//System.out.println(11111);
					if(map.get("err_code").equals("-4444")) {			
						throw new NotExsistExcpetion("Out User/-2");
					}
					if(map.get("err_code").equals("77777")) {			
						//System.out.println(22222);
						throw new NotExsistExcpetion("Not User/-1");
					}
					throw new OracleError("Not regist/-9");
				}
				//System.out.println("유저 확인후 :  "+map);
			}else {
				System.out.println("쿠키를 만듭시다.");
				String token=tokenService.createToken(uservo);
				System.out.println("webber "+token);
				
				MakeCookie mc=new MakeCookie();
				mc.makeCookie(req, resp, token);
					
			}
		}
		return userVo;
		
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
		
		String filePath="D:\\uploadFile\\profilePhoto\\";
		filePath+=nickName;
		
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) req;
		Iterator<String> iterator=mhsr.getFileNames();//Iterator 갯수 체크
		MultipartFile mf=null; 
		while (iterator.hasNext()) {
			//img파일 저장 위치
			
			File file=new File(filePath);
			
			if(file.exists()) {
				file.delete();
			}
			
			if(!file.exists()) {
				file.mkdirs();
			}
			
			System.out.println("파일 생성");
					
				}
	    String sFileName=uif.uploadImg(req, filePath);
	    if(sFileName!=null) {
		map.put("thumbnail", "/tImg/profilePhoto/"+nickName+"/"+sFileName);
		userDao.updateInfo(map);
		
			UserVo uservo=userDao.checkUser(map);
			String token=tokenService.createToken( uservo);
			MakeCookie mc=new MakeCookie();
			mc.makeCookie(req, resp, token);			
		 
			/*HttpSession session=req.getSession();
			session.invalidate();
			Map<String,Object> detailInfo=tokenService.get(token);
			MakeSession ms=new MakeSession();
			ms.makeSession(detailInfo, req, resp);*/			
		
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
