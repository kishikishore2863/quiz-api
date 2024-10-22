package com.kishi.quiz_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AuthResponse {
    private String jwt;
    private String message;
    public AuthResponse(String jwt ,String message){
        super();
        this.jwt = jwt;
        this.message = message;
    }
}
