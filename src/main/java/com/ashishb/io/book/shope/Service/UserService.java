package com.ashishb.io.book.shope.Service;

import com.ashishb.io.book.shope.Model.Role;
import com.ashishb.io.book.shope.Model.User;
import com.ashishb.io.book.shope.Repository.IRoleRepo;
import com.ashishb.io.book.shope.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepo  userRepository;

    @Autowired
    private IRoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//SAVE USER
    @Override
    public User saveUser(User user){

        Set<Role> userRole = new HashSet<>();
        Role defaultRole = new Role();
        defaultRole.setRoledes("default role for new user");
        defaultRole.setRolename("USER");
        roleRepo.save(defaultRole);

        userRole.add(defaultRole);
        user.setCreateTime(LocalDateTime.now());
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }

//   FIND BY USER NAME
    @Override
    public Optional<User> findUserByUsername(String username){

        return  userRepository.findByUsername(username);
    }

// MAKE ADMIN
    @Override
    @Transactional
    public void makeAdmin(String username){
//       User user =  userRepository.findByUsername(username).orElseThrow(
//               ()-> new UsernameNotFoundException("user with the name : "+username+" doesn't exist")
//       );
        User user = userRepository.findByUsername(username).get();

        Role newRole = new Role();
        newRole.setRolename("ADMIN");
        newRole.setRoledes("power user");
        Role u  =roleRepo.save(newRole);
        System.out.println(u);
        Set<Role> updatedRole = new HashSet<>();
        updatedRole.add(newRole);
        user.setRole(updatedRole);
        userRepository.save(user);

    }


}
