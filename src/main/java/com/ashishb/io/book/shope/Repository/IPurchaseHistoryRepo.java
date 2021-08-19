package com.ashishb.io.book.shope.Repository;

import com.ashishb.io.book.shope.Model.PurchaseHistory;
import com.ashishb.io.book.shope.Repository.Projection.IPurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPurchaseHistoryRepo extends JpaRepository<PurchaseHistory, Long> {

    @Modifying
    @Query( value ="select "+
            "b.title as title, ph.price as price, ph.purchase_time as purchaseTime from PurchaseHistory ph " +
            "left join Book b on b.id = ph.book_id where ph.user_id = :userId", nativeQuery=true)
    List<IPurchaseItem> findAllPurchasesOfUser(@Param("userId") Long userId);
}
