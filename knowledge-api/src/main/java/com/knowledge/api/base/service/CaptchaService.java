package com.knowledge.api.base.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.knowledge.api.base.service.KaptchBean.KaptchBean;

import io.netty.handler.codec.base64.Base64Encoder;
import sun.misc.BASE64Encoder;

@Service
public class CaptchaService {
	
	private Logger log = LogManager.getLogger(CaptchaService.class);
	@Value("60000")
	private Integer timeout;

	@Autowired
	private DefaultKaptcha producer;

	@Autowired
	protected StringRedisTemplate redisTemplate;

	@SuppressWarnings("restriction")
	public KaptchBean createKaptch() {
		// 生成一个token
		String cToken = UUID.randomUUID().toString();
		// 生成文字验证码
		String captcha = producer.createText();
		// 生成验证码对应的token 以token为key 验证码为value存在redis中
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(cToken, captcha);
		redisTemplate.expire(cToken, timeout, TimeUnit.MINUTES);
		// 生成图片验证码
		ByteArrayOutputStream outputStream = null;
		BufferedImage image = producer.createImage(captcha);
		outputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", outputStream);
		} catch (IOException e) {
			log.info("createKaptch : {}"+e.getMessage());
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String base64Image = encoder.encode(outputStream.toByteArray());
		base64Image = base64Image.replaceAll("[\\s*\t\n\r]", "");
		KaptchBean bean = new KaptchBean();
		bean.setcToken(cToken);
		bean.setExpire(timeout);
		bean.setImage("data:image/jpeg;base64," + base64Image);
		return bean;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean checkKaptch(String cToken,String captcha) {
		 ValueOperations operations = redisTemplate.opsForValue();
	       //判断验证码是否还存在
	        if(redisTemplate.hasKey(cToken)){
	            if(operations.get(cToken).equals(captcha)){
	                //验证通过之后删除对应的key
	                redisTemplate.delete(cToken);
	                return true;
	            }
	        }
	        return false;
	}
}
