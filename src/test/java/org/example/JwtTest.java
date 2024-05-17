package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","zhangsan");
        //生成JWT
       String token = JWT.create().withClaim("user",claims)
               .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))
               .sign(Algorithm.HMAC256("yuanshen"));
       System.out.println(token);
    }

    @Test
    public void testVerify(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6InpoYW5nc2FuIn0sImV4cCI6MTcxNTgwMzcyMn0.id7o1WAxOoqio38d0DpWS5no0pzCRrtwsPZpCJslsqo";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("yuanshen")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim user = claims.get("user");
        System.out.println(user.asMap().get("id"));
        System.out.println(user.asMap().get("username"));
    }
}
