package com.ashishb.io.book.shope.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLE",
    joinColumns = {@JoinColumn(name="USER_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> role;
}
