package com.xws.nistagrammonolith.security;

import java.io.Serializable;

public class Token implements Serializable {
    private final String jwtToken;

    public Token(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
