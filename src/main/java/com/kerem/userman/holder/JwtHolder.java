package com.kerem.userman.holder;

import org.springframework.stereotype.Component;

@Component
public class JwtHolder {

    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}