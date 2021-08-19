package com.ashishb.io.book.shope.Repository.Projection;

import java.time.LocalDateTime;

public interface IPurchaseItem {

    String getTitle();
    Double getPrice();
    LocalDateTime getPurchaseTime();

}
