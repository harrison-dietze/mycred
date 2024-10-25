package com.harri.modules.auth.utils;

import java.util.Arrays;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenGenerator {

    public static void main(String[] args) {
        System.out.println(generateToken("Harrison"));
    }

    public static String generateToken(String username) {
        String token =
                Jwt.issuer("https://example.com/issuer")
                        .upn(username)
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .sign();
        return token;
    }

}