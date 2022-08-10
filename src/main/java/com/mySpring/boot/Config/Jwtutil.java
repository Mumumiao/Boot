package com.mySpring.boot.Config;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Setter
@Getter
public class Jwtutil {
    @Autowired
    private JwtConfig jwtConfig;

    public String createJwt(int roleid, int id) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, jwtConfig.getTime());

        Map<String, Object> payload = new HashMap<String, Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("id", id);
        payload.put("roleid", roleid);
        String token = JWTUtil.createToken(payload, jwtConfig.getSecret().getBytes());

        return token;


    }

    public boolean verifyJWT(String token) {
        Boolean judge = false;
        JWT jwt = JWTUtil.parseToken(token);
        boolean verifyKey = jwt.setKey(jwtConfig.getSecret().getBytes()).verify();
        System.out.println(verifyKey);
        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);
        if (verifyKey && verifyTime) {
            judge = true;
        }
        return judge;
    }


}
