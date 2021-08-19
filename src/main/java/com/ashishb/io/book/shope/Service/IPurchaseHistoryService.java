package com.ashishb.io.book.shope.Service;

import com.ashishb.io.book.shope.Model.PurchaseHistory;
import com.ashishb.io.book.shope.Repository.Projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {
    //  save
    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

    //   GET PURCHASEHISTORY OF USER
    List<IPurchaseItem> findPurchaseItemsOfUser(Long userId);
}
