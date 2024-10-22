package com.kishi.quiz_api.service;

import com.kishi.quiz_api.dto.AuthResponse;
import com.kishi.quiz_api.entity.User;
import com.kishi.quiz_api.exception.UserException;

public interface UserService {
    public AuthResponse registerUser(User user) ;
    public String login(User user);
}
