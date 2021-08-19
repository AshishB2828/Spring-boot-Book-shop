package com.ashishb.io.book.shope.Controller;


import com.ashishb.io.book.shope.Dto.LoginRequest;
import com.ashishb.io.book.shope.Model.User;
import com.ashishb.io.book.shope.Service.AuthService;
import com.ashishb.io.book.shope.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest){


        return  new ResponseEntity<>(authService.SignInAndGenerateToken(loginRequest), HttpStatus.OK);
    }
    @PostMapping("signup")
    public ResponseEntity<?> signUp (@RequestBody User user){
        if(userService.findUserByUsername(user.getUsername()).isPresent()){
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(authService.saveUser(user), HttpStatus.OK);
    }
}
