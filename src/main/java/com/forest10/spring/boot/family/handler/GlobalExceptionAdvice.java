package com.forest10.spring.boot.family.handler;

import com.forest10.spring.boot.family.api.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @author Forest10
 * @date 2018/8/15 下午3:44
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionAdvice {

	/**
	 * 200 - OK
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
	public Serializable handleArgumentNotValidException(Exception e) {
		log.error("参数绑定错误!", e);
		//1.拿到绑定结果
		BindingResult bindingResult;
		if (e instanceof BindException) {
			bindingResult = ((BindException) e).getBindingResult();
		} else {
			bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
		}
		//2.拿到所有绑定错误的Field
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		//此时可以选择是直接把这些错误返回还是公共处理。比如公有的错误码之类的。
		StringBuilder err = new StringBuilder();
		for (FieldError error : fieldErrors) {
			err.append(error.getField()).append(error.getDefaultMessage()).append(";");
		}
		return JsonResult.error(err.toString());
	}

	/**
	 * 200 - OK
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = {Exception.class})
	public Serializable handleException(Exception e) {
		log.error("未知错误!", e);
		return JsonResult.error("未知错误");
	}


}
