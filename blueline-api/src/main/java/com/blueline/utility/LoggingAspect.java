package com.blueline.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.aspectj.lang.annotation.AfterThrowing;

@Slf4j
public class LoggingAspect {
	@AfterThrowing(pointcut = "execution(* com.blueline.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception)
			throws Exception {
		log.error(exception.getMessage(), exception);
	}
}
