package com.ashishb.io.book.shope.Repository;

import com.ashishb.io.book.shope.Model.Book;
import com.ashishb.io.book.shope.Model.Role;
import com.ashishb.io.book.shope.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {


}
