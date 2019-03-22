package com.knowledge.admin.base.config.aop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.feilong.core.Validator;
import com.knowledge.admin.base.loginlog.entity.TbLoginInfo;
import com.knowledge.admin.base.loginlog.service.ITbLoginInfoService;
import com.knowledge.admin.base.util.SessionUtil;

@Aspect
@Component
public class LoginAop {

    @Resource
    private ITbLoginInfoService loginLogService;

    @Pointcut(value="execution(* com.knowledge.admin.base.login.IndexController.doLogin(..))))")
    private void loginLog() {
    }
    
    @After("loginLog()")
    public void doAfter(JoinPoint joinPoint) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	HttpServletRequest request = attributes.getRequest();
    	String account = (String)SessionUtil.getAttr("account");
    	String msg = (String)SessionUtil.getAttr("msg");
    	 if(Validator.isNotNullOrEmpty(msg)){
    		 this.writeLoginLog("登錄失敗",msg,account,request);
         }else{
        	 this.writeLoginLog("登錄成功",null,account,request);
         }
    }

    private void writeLoginLog(String status,String msg,String account,HttpServletRequest request) {
        TbLoginInfo loginLog = new TbLoginInfo();
        loginLog.setStatus(status);
        loginLog.setAccountName(account);
        loginLog.setLoginTime(new Date());
        loginLog.setIp(getIpAddress(request));
        loginLog.setRemark(msg);
        loginLogService.insert(loginLog);
    }
    
    /**
     * 获取ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }
}
