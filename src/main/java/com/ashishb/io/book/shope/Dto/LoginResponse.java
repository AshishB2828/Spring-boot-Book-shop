package com.ashishb.io.book.shope.Dto;

import com.ashishb.io.book.shope.Model.Role;
import com.ashishb.io.book.shope.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {

    private Long id;
    private String username;
    private String name;
    private String token;
    private List<?> roles;
}
