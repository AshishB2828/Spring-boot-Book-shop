package com.ashishb.io.book.shope.Controller;

import com.ashishb.io.book.shope.Model.User;
import com.ashishb.io.book.shope.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserControler {
    @Autowired
    private IUserService userService;

    @PostMapping("signup")
    public ResponseEntity<User> signUp(@RequestBody User user){

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

//    @PostMapping("signin")
//    public ResponseEntity<User> signIn(@RequestBody User user){
//
//        return  new ResponseEntity<>(authenticationService.SignInAndReturnJWT(user),HttpStatus.OK);
//    }
    @GetMapping("{username}")
    public ResponseEntity<String> chnageRole(@PathVariable("username") String username){
        userService.makeAdmin(username);
        return  new ResponseEntity<>("your now changed to ADMIN", HttpStatus.OK);
    }
}
