package com.kishi.quiz_api.controller;


import com.kishi.quiz_api.dto.AuthResponse;
import com.kishi.quiz_api.entity.User;
import com.kishi.quiz_api.exception.UserException;
import com.kishi.quiz_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user)  {
     AuthResponse res = userService.registerUser(user);
       return new ResponseEntity<AuthResponse>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        return "Sucess";
    }
}
