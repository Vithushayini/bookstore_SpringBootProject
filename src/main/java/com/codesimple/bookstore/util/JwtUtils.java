package com.codesimple.bookstore.util;

import com.codesimple.bookstore.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtUtils {
    private static String secret="ThisIsSecretxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static long expiryDuration= 60 * 60;
    public String generateJwt(User user){

        long milliTime=System.currentTimeMillis();
        long expiryTime=milliTime+expiryDuration*1000;

        Date issuedAt =new Date(milliTime);
        Date expiryAt =new Date(expiryTime);


        //claims
        ClaimsBuilder claims=Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        //optional claims

        claims.add("type",user.getUserType());
        claims.add("name",user.getName());
        claims.add("emailId",user.getEmailId());

        //generate jwt using claims
        return Jwts.builder()
                .setClaims(claims.build())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }
}
