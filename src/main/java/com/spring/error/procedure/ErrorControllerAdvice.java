package com.spring.error.procedure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.spring.error.NotExsistExcpetion;
import com.spring.error.NotFoundError;
import com.spring.error.OracleError;
import com.spring.error.UnAuthException;
import com.spring.error.msg.ErrorMessage;



@ControllerAdvice
public class ErrorControllerAdvice {
	
	@ExceptionHandler(value= {NotExsistExcpetion.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ErrorMessage handleBadRequest(RuntimeException ex,WebRequest wr) {
		ErrorMessage em=new ErrorMessage();
		em.setStatus(HttpStatus.BAD_REQUEST.toString());
		String[]erroArr=ex.getMessage().split("/");
		em.setMessage(erroArr[0]);
		em.setCode(Integer.parseInt(erroArr[1]));
		
		//System.out.println(em);
		
		return em;
		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value= {NotFoundError.class})
	@ResponseBody
	protected ErrorMessage NotFoundRequest(RuntimeException ex,WebRequest wr) {
		ErrorMessage em=new ErrorMessage();
		em.setStatus(HttpStatus.NOT_FOUND.toString());
		String[]erroArr=ex.getMessage().split("/");
		em.setMessage(erroArr[0]);
		em.setCode(Integer.parseInt(erroArr[1]));
		return em;
		
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value= {OracleError.class})
	@ResponseBody
	protected ErrorMessage ServerError(RuntimeException ex,WebRequest wr) {
		ErrorMessage em=new ErrorMessage();
		em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		String[]erroArr=ex.getMessage().split("/");
		em.setMessage(erroArr[0]);
		em.setCode(Integer.parseInt(erroArr[1]));
		return em;
		
	}
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(value= {UnAuthException.class})
	@ResponseBody
	protected ErrorMessage notAuthError(RuntimeException ex,WebRequest wr) {
		ErrorMessage em=new ErrorMessage();
		em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		String[]erroArr=ex.getMessage().split("/");
		em.setMessage(erroArr[0]);
		em.setCode(Integer.parseInt(erroArr[1]));
		return em;
		
	}
}
