package com.knowledge.common.base.config.token;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.knowledge.common.business.member.entity.TbMember;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * @author jinbin
 * @date 2018-07-08 21:04
 */
@Service("TokenService")
public class TokenService {
    public String getToken(TbMember user) {
        String token="";
        token= JWT.create().withAudience(user.getId().toString())
        		//过期时间24小时
        		.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))//60 * 60 * 24 * 1000 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        
  /*    token= Jwts.builder()
        .setSubject(user.getId().toString())
        .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
        .signWith(SignatureAlgorithm.HS512, user.getPassword())
        .compact();*/

        return token;
    }
}
