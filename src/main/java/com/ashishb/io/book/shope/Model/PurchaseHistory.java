package com.ashishb.io.book.shope.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchasehistory")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime;
}
