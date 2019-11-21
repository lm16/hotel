package com.hotel.exceptionHandler;


import com.hotel.bean.Result;
import com.hotel.bean.ResultType;
import com.hotel.utils.LogUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExtHanler {

	/**
	 * shiro 权限拦截
	 */
	private LogUtils logUilts = new LogUtils(this.getClass());
	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	public Result handleShiroException(Exception ex,HttpServletRequest request) {
		logUilts.logException(ex);
		return Result.build(ResultType.Unauthorized,"账户未授权");
	}
	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	public Result AuthorizationException(Exception ex,HttpServletRequest request) {
		logUilts.logException(ex);
		return Result.build(ResultType.Continue, "权限认证失败,请登录账户");
	}

	/*
	 * 全局异常处理器
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result handlerException(Exception e, HttpServletRequest request) {
		logUilts.logException(e);
		return Result.build(ResultType.Exception, e.getMessage()).appendData("Exception", e.getClass().getName());
	}


}
