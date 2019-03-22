package com.knowledge.api.base.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.feilong.core.Validator;
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.knowledge.common.business.member.entity.TbBindingUser;
import com.knowledge.common.business.member.service.ITbBindingUserService;

/**
 * @author jide
 * @date 2018-12-04
 */
@Service
public class GoogleService {

	private Logger log = LogManager.getLogger(GoogleService.class);
	
	@Value("${google.clientId}")
	private String clienId;
	@Autowired
	private ITbBindingUserService iBindingUserService;

	public Integer verifyToken(String idtokenstr) {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance()).setAudience(Collections.singletonList(clienId)).build();
			GoogleIdToken idToken;
			try {
				idToken = verifier.verify(idtokenstr);
				if (Validator.isNotNullOrEmpty(idToken)) {
					Payload payload = idToken.getPayload();
					String userId = payload.getSubject();
					String email = (String) payload.get("email");
					String name = (String) payload.get("name");
					TbBindingUser user = new TbBindingUser();
					user.setAboutMe(email);
					user.setPlatformId(userId);
					user.setPlatformName(name);
					user.setPlatform(2);
					iBindingUserService.insert(user);
					return user.getId().intValue();
				}
			} catch (GeneralSecurityException e) {
				log.error("verifyToken {}:"+e.getMessage());
				
			} catch (IOException e) {
				log.error("verifyToken {}:"+e.getMessage());
			}
			return null;
			
		
		
	}

}
