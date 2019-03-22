package com.knowledge.api.base.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController{

  @ExceptionHandler(value = KaptchaException.class)
  public Result kaptchaExceptionHandler(KaptchaException kaptchaException) {
    if (kaptchaException instanceof KaptchaIncorrectException) {
      return fail(EnumResult.ERROR_KAPTCHA);
    } else if (kaptchaException instanceof KaptchaNotFoundException) {
      return fail(EnumResult.ERROR_KAPTCHA);
    } else if (kaptchaException instanceof KaptchaTimeoutException) {
      return fail(EnumResult.ERROR_KAPTCHA_TIMEOUT);
    } else {
      return fail(EnumResult.ERROR_KAPTCHA_FAIL);
    }

  }

}