package com.kishi.quiz_api.controller;




import com.kishi.quiz_api.config.JwtProvider;
import com.kishi.quiz_api.dto.AuthResponse;
import com.kishi.quiz_api.dto.LoginRequest;
import com.kishi.quiz_api.entity.User;
import com.kishi.quiz_api.exception.UserException;
import com.kishi.quiz_api.repository.UserRepository;
import com.kishi.quiz_api.service.CustomerUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    private CustomerUserServiceImpl customerUserServiceImpl;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomerUserServiceImpl customerUserServiceImpl, JwtProvider jwtProvider ){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.customerUserServiceImpl=customerUserServiceImpl;
        this.jwtProvider= jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user)throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String  username = user.getUsername();



        User isEmailExist =userRepository.findByEmail(email);
        if(isEmailExist!=null){
            throw new UserException("Email is Already Used with Another Account");
        }
        User createdUser =new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setUsername(username);


        User savedUser =userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =jwtProvider.generateToken(authentication);

        AuthResponse authResponse =new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup Sucess");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password =loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(token,"Signup Success");
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password){
        UserDetails userDetails =customerUserServiceImpl.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}