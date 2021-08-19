package com.ashishb.io.book.shope.Service;


import com.ashishb.io.book.shope.Dto.LoginRequest;
import com.ashishb.io.book.shope.Dto.LoginResponse;
import com.ashishb.io.book.shope.Jwt.JwtProvider;
import com.ashishb.io.book.shope.Model.Role;
import com.ashishb.io.book.shope.Model.User;
import com.ashishb.io.book.shope.Repository.IRoleRepo;
import com.ashishb.io.book.shope.Repository.IUserRepo;
import com.ashishb.io.book.shope.Security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService implements IAuthService{

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private IUserRepo  userRepository;

    @Autowired
    private IRoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public LoginResponse SignInAndGenerateToken(LoginRequest loginRequest){

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        authenticateUser(username, password);

    final UserDetails userDetails = customUserDetails.loadUserByUsername(username);
//    System.out.println(userDetails);
    String JwtToken =  jwtProvider.generateJwtToken(userDetails);

    User userExist = userRepo.findByUsername(username).orElseThrow(()->
            new UsernameNotFoundException("User is not Exist"));
    List<Role> userRoles =  userExist.getRole().stream().collect(Collectors.toList());

    return LoginResponse.builder()
            .id(userExist.getId())
            .name(userExist.getName())
            .username(userExist.getUsername())
            .token(JwtToken)
            .roles(userRoles)
            .build();

    }

    private void authenticateUser(String username, String password) {

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username, password));

        }catch (DisabledException e){
            System.out.println(username+" is disabled");
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Error");
        }
    }

    @Override
    public User saveUser(User user){

        Set<Role> userRole = new HashSet<>();
        Role defaultRole = new Role();
        defaultRole.setRoledes("default role for new user");
        defaultRole.setRolename("USER");
        roleRepo.save(defaultRole);

        userRole.add(defaultRole);
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(userRole);
        return  userRepository.save(user);
    }

}
