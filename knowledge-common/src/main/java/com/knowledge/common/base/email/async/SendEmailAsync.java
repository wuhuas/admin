package com.knowledge.common.base.email.async;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.feilong.core.Validator;
import com.knowledge.common.base.email.service.IMailService;
import com.knowledge.common.business.member.entity.TbMember;
import com.knowledge.common.business.notice.entity.TbNotice;
import com.knowledge.common.business.periodical.entity.TbSelectedPeriodical;

@Component
public class SendEmailAsync {
	private static Logger log = LoggerFactory.getLogger(SendEmailAsync.class);
	@Autowired
	private IMailService mailService;

	@Autowired
	private TemplateEngine templateEngine;

	
	
	/**
	 * 杂志订单管理 
	 * 审核通过/失败  发送邮件通知
	 * @param email 发送邮箱
	 * @param status true=审核通过   false=审核失败
	 * @param passworedNew=0 不用发送账号密码过去
	 */
	@Async
	public void sendMagazineStatusByEmail(String email,String name,Integer type,Boolean status,String passworedNew){
		Context context = new Context();
		context.setVariable("name", name);
		String orderType = (type == 1) ? "付費訂購" : "索取贈閱";
		String content = "";
		try {
			if(status == true){
				
				
				if("0".equals(passworedNew)){
					content = "您好！您"+orderType+"的知識雜誌已成功通過審核！您的訂閱信息將在下一個月開始生效";
				}else{
					content = "您好！您"+orderType+"的知識雜誌已成功通過審核！您的訂閱信息將在下一個月開始生效。登錄賬號："+email+"   登錄密碼："+passworedNew;
				}
				
			}else{
				content = "您好！您"+orderType+"的知識雜誌審核失敗！請重新提交訂閱人的信息！";
			}
			context.setVariable("content", content);
			String emailContent = templateEngine.process("emailMagazine", context);
			mailService.sendHtmlMail(email, "【知識網】雜誌訂單審核通知", emailContent);
		} catch (MessagingException e) {
			log.debug("ERROR:雜誌訂單狀態发送失败： {}" + e.getMessage());
		}
		
	}
	
	
	
	/*
	 * @SuppressWarnings("unused") private static String changeEncode(String str) {
	 * try { str = new String(str.getBytes("ISO-8859-1"), "UTF-8"); // "B"代表Base64 }
	 * catch (UnsupportedEncodingException e) { log.error(e.getMessage()); } return
	 * str; }
	 */

	@Async()
	public void sendEmail(TbMember member, Integer type) {
		String hostName = member.getHostName();
		String code = member.getActiveCode();
		String email = member.getEmail();
		String name = member.getNickname();
		Context context = new Context();
		switch (type) {
		case 1:
			// 注册发动邮件
			context.setVariable("nickName", name);
			context.setVariable("url", hostName+"#/login?code="+code);
			String emailContent = templateEngine.process("emailActive", context);
			try {
				mailService.sendHtmlMail(email, "電子郵箱驗證", emailContent);
			} catch (MessagingException e) {
				log.error("電子郵箱驗證发送失败： {}" + e.getMessage());
			}
			break;
		case 2:
			// 密码修改发送邮件
			context.setVariable("nickName", name);
			context.setVariable("url", hostName+"#/forgetPsw?code="+code);
			context.setVariable("homeUrl", hostName+"#/home");
			String emailupdate = templateEngine.process("emailForgetPow", context);
			try {
				mailService.sendHtmlMail(email, "電子郵箱驗證", emailupdate);
			} catch (MessagingException e) {
				log.error("密码修邮件发送失败： {} " + e.getMessage());
			}
			break;
		case 3:
			break;
		default:
			break;
		}
	}

	/**
	 * 訂閱電子雜誌的郵件發送
	 */
	@Async
	@Transactional
	public void sendEleMagazine(TbSelectedPeriodical selectedperiodical,String coverUrl,List<TbMember> members) {
		log.debug(">>>>>>>>>>>>>>>>>>進入：發送郵件異步");
		String releaseMonth = selectedperiodical.getReleaseMonth();
		String nextMonth = "";
		if (Validator.isNotNullOrEmpty(releaseMonth)) {
			String[] split = releaseMonth.split("-");
			releaseMonth = split[0] + "年" + split[1] + "月";
			
			Integer parse = Integer.parseInt(split[1]);
			Integer month = 0;
			if(parse == 11) {
				month = 1;
			}else if(parse == 12){
				month = 2;
			}else {
				month = parse + 2;
			}
			nextMonth  = split[0] + "年" + month + "月";
		}
		Context context = new Context();
		context.setVariable("coverUrl", coverUrl);
		context.setVariable("releaseMonth", releaseMonth);
		context.setVariable("nextMonth", nextMonth);
		context.setVariable("url", "https://www.upknowledge.com.hk/#/perDetail?id="+selectedperiodical.getId());
		for (TbMember member : members) {
			context.setVariable("unsubUrl", "http://www.upknowledge.com.hk/#/unsubscribe?email="+member.getSubEmail());
			context.setVariable("nickName", member.getNickname());
			String emailupdate = templateEngine.process("emailActive", context);
			try {
				mailService.sendHtmlMail(member.getSubEmail(), "最新一期香港《知識》雜誌電子版上線啦！", emailupdate);
				log.debug("SUCCESS:-------------> 電子雜誌邮件发送成功!! 郵箱：{}",member.getSubEmail());
			} catch (MessagingException e) {
				TbNotice notice = new TbNotice();
				notice.setTitle("系統通知");
				notice.setContent("最新一期香港《知識》電子雜誌上線邮件通知发送失败！請仔細核對您填寫的電子雜誌訂閱更新通知郵箱并及時修改！");
				notice.setCreateTime(new Date());
				notice.setStatus(2);//未讀
				notice.setToMemId(member.getId());
				notice.insert();
				log.debug("ERROR:----------> 電子雜誌邮件发送失败!! 郵箱：{},错误信息：{} ", member.getSubEmail(),e.getMessage());
			}
		}
		log.debug(">>>>>>>>>>>>>>>>>>結束：發送郵件異步");
	}
	
	
	
	/**
	 * 發送自定義郵件
	 * @param selectedperiodical
	 * @param coverUrl
	 * @param members
	 */
	@Async
	@Transactional
	public void sendCusEmail(String title,String content,List<String> emails) {
		log.debug(">>>>>>>>>>>>>>>>>>進入：發送自定義郵件異步");
		Context context = new Context();
		context.setVariable("title", title);
		context.setVariable("content", content);
		for (String email : emails) {
			String emailupdate = templateEngine.process("emailTemplate", context);
			try {
				mailService.sendHtmlMail(email, title, emailupdate);
				log.debug("SUCCESS:-------------> 邮件发送成功!! 郵箱：{}",email);
			} catch (MessagingException e) {
				log.debug("ERROR:----------> 邮件发送失败!! 郵箱：{},错误信息：{} ", email,e.getMessage());
			}
		}
		log.debug(">>>>>>>>>>>>>>>>>>結束：發送自定義郵件異步");
	}
}
