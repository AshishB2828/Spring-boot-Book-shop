package com.ashishb.io.book.shope.Repository;

import com.ashishb.io.book.shope.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleRepo extends JpaRepository<Role, String> {


}
