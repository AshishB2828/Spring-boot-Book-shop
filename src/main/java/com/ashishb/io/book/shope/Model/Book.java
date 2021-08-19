package com.ashishb.io.book.shope.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name="description", nullable = false, length = 1000)
    private String description;

    @Column(name="author", nullable = false, length = 100)
    private String author;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;


}
