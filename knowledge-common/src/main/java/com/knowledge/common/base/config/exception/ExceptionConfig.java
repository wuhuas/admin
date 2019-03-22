package com.knowledge.common.base.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.knowledge.common.base.index.BaseController;


/**
 * 异常集中处理
 * @author francis
 *
 */
@ControllerAdvice
public class ExceptionConfig extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(ExceptionConfig.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ParamsException.class)
	public ResponseEntity ecxeptionHander(ParamsException exception) {		
		ResponseEntity.badRequest().build();
		exception.printStackTrace();
		log.debug("ERROR:--------------"+exception.getMessage());
		return ResponseEntity.ok().body(fail(exception.getMessage(),"-1"));
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity businessException(BusinessException exception) {
//	    exception.printStackTrace();
	    log.debug("ERROR:--------------"+exception.getResult().toString());
		return ResponseEntity.ok().body(fail(exception.getResult()));
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public ResponseEntity exception(Exception exception) {
	    exception.printStackTrace();
	    log.debug("ERROR:--------------"+exception.getMessage());
		return ResponseEntity.ok().body(error());
	}
	
}
