package com.example.springboot.snow.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.snow.model.Users;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service("TokenService")
public class TokenService {
    public String getToken(long ttlMillis,Users user) throws UnsupportedEncodingException {
        String token="";
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = null;
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            exp = new Date(expMillis);
            //设置过期时间
        }
        if (exp != null) {
            token= JWT.create().withAudience(String.valueOf(user.getId()))// 将 user id 保存到 token 里面
                    .withExpiresAt(exp)// 设置有效时间
                    .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        } else {
            token= JWT.create().withAudience(String.valueOf(user.getId()))// 将 user id 保存到 token 里面
                    .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        }

        return token;
    }
}
