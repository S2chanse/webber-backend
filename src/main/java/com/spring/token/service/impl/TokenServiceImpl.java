package com.spring.token.service.impl;

import java.io.UnsupportedEncodingException;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.spring.error.Unauthorization;
import com.spring.token.service.TokenService;
import com.spring.user.vo.UserVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
	private static final String SALT =  "$dfad@efdvxcvadsfgA5465RRTER78@wEBBER";
	@Override
	public String createToken(UserVo uservo) {
		//System.out.println("erawerawerawerawerawer");
		   Calendar cal=Calendar.getInstance();
		   cal.setTime(new Date());
		   		
	       Date d = cal.getTime();
	       UserVo keyUser=new UserVo();
	       keyUser.setNickname(uservo.getNickname());
	       keyUser.setThumbnail(uservo.getThumbnail());
	       keyUser.setAuth(uservo.getAuth());
		String jwt=Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(d)
				.claim("webberUser",keyUser)
				.signWith(SignatureAlgorithm.HS256, this.genereateKey())
				.compact();  
		return jwt;
		
	}
	
	private byte[] genereateKey() {
		byte[] key=null;
		try {
			key=SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	@Override
	public boolean isUsable(String token) {
		try {
		Jws<Claims> claims=Jwts.parser().setSigningKey(this.genereateKey()).parseClaimsJws(token);
		
		System.out.println("유효한값입니다.");
		return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("유효하지 않습니다.");
			return false;
		}
	}
	
	@Override
	public Map<String, Object> get(String token) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = token;
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (Exception e) {
			
			throw new Unauthorization();

		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get("webberUser");
		return value;
	}

	@Override
	public Calendar getExp(String token) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = token;
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (Exception e) {
			
			throw new Unauthorization();

		}
		@SuppressWarnings("unchecked")
		Date date =  claims.getBody().getExpiration();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

 
	
}
