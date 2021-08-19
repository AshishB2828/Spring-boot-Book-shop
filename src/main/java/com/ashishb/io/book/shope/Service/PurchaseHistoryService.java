package com.ashishb.io.book.shope.Service;

import com.ashishb.io.book.shope.Model.PurchaseHistory;
import com.ashishb.io.book.shope.Repository.IPurchaseHistoryRepo;
import com.ashishb.io.book.shope.Repository.Projection.IPurchaseItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class PurchaseHistoryService implements IPurchaseHistoryService{
    @Autowired
    private IPurchaseHistoryRepo purchaseHistoryRepository;

    // SAVE
    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory){

        purchaseHistory.setPurchaseTime(LocalDateTime.now());

        return  purchaseHistoryRepository.save(purchaseHistory);
    }

    //   GET PURCHASEHISTORY OF USER
    @Override
    public List<IPurchaseItem> findPurchaseItemsOfUser(Long userId){

        return  purchaseHistoryRepository.findAllPurchasesOfUser(userId);
    }
}
