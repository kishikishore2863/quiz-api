package com.kishi.quiz_api.service.implementation;

import com.kishi.quiz_api.config.JwtProvider;
import com.kishi.quiz_api.dto.AuthResponse;
import com.kishi.quiz_api.entity.User;
import com.kishi.quiz_api.exception.UserException;
import com.kishi.quiz_api.repository.UserRepository;
import com.kishi.quiz_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;


//    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    @Autowired
    public UserImpl(UserRepository userRepository,JwtProvider jwtProvider,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.jwtProvider=jwtProvider;
        this.passwordEncoder=passwordEncoder;

    }

    @Override
    public AuthResponse registerUser(User user)  {
//        user.setPassword(encoder.encode(user.getPassword()));

        String email = user.getEmail().toLowerCase();
        String rawPassword = user.getPassword();
        String username = user.getUsername();


        User isEmailExist =userRepository.findByEmail(user.getEmail());
        if(isEmailExist != null){
            throw new RuntimeException("Email already registered");
        }

        User createdUser =new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(rawPassword));
        createdUser.setUsername(username);

        User savedUser =userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =jwtProvider.generateToken(authentication);

        return new AuthResponse(token,"user registered success");

    }

    @Override
    public String login(User user) {
        return null;
    }
}
