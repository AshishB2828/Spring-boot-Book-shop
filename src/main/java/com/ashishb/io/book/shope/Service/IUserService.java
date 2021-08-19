package com.ashishb.io.book.shope.Service;

import com.ashishb.io.book.shope.Model.User;

import java.util.Optional;

public interface IUserService  {
    //SAVE USER
    User saveUser(User user);

    //   FIND BY USER NAME
    Optional<User> findUserByUsername(String username);

    // MAKE ADMIN
    void makeAdmin(String username);
}
