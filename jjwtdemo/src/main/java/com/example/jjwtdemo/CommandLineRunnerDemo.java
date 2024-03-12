package com.example.jjwtdemo;

import javax.crypto.SecretKey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.jjwtdemo.JjwtdemoApplication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

@Configuration
public class CommandLineRunnerDemo {
    @Bean
    public CommandLineRunner run() {
        return args -> {

            System.out.println("");

            SecretKey key = Jwts.SIG.HS256.key().build();

            String secretString = Encoders.BASE64.encode(key.getEncoded());

            String jws = Jwts.builder().subject("Joe").signWith(key).compact();

            System.out.println("key :" + key);

            System.out.println("secretString :" + secretString);

            System.out.println("Jws :" + jws);

            Boolean result = Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject().equals("Joe");

            System.out.println("result :" + result);     
        };
    }
}
