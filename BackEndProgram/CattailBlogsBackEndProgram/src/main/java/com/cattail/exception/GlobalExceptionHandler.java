package com.cattail.exception;

import com.cattail.utility.Result;
import com.cattail.utility.ResultCodeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/19 23:11
 * @description 全局异常处理
 */

@Slf4j  // log
@RestControllerAdvice
//@RestControllerAdvice本质上是个Component，用于定义@ExceptionHandler，@InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法。
public class GlobalExceptionHandler {
	
	/**
	 * 运行时异常处理器
	 *
	 * @param e ShiroException
	 * @return Result.fail()
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = ShiroException.class)  // 异常处理器
	public Result handler(ShiroException e) {
		log.error("运行时异常：------------------>{}", e);
		Result result = Result.fail(ResultCodeType.ok);
		result.setCode(401);
		result.setData(e.getMessage());
		return result;
	}
	
	/**
	 * 实体校验异常处理器
	 *
	 * @param e MethodArgumentNotValidException
	 * @return Result.fail()
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Result handler(MethodArgumentNotValidException e) {
		log.error("实体校验异常---------------->{}", e);
		BindingResult bindingResult = e.getBindingResult();
		ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
		return Result.fail(ResultCodeType.notFound, objectError.getDefaultMessage());
	}
	
	/**
	 * Assert异常
	 *
	 * @param e IllegalArgumentException
	 * @return Result.fail()
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public Result handler(IllegalArgumentException e) {
		log.error("Assert异常：-------------------->{}", e);
		return Result.fail(ResultCodeType.notFound, e.getMessage());
	}
	
	/**
	 * 运行时异常
	 *
	 * @param e RuntimeException
	 * @return Result.fail()
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = RuntimeException.class)
	public Result handler(RuntimeException e) {
		log.error("运行时异常：--------------------->{}", e);
		return Result.fail(ResultCodeType.notFound, e.getMessage());
	}
}
