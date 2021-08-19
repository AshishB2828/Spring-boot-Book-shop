package com.ashishb.io.book.shope.Security;

import com.ashishb.io.book.shope.Model.User;
import com.ashishb.io.book.shope.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private IUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with the name "+username)
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),getAuthorities(user)
        );
    }

    private Set getAuthorities(User user) {

        Set authorities =new HashSet();

        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority ("ROLE_"+role.getRolename()));
        });
        return  authorities;
    }
}
