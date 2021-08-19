package com.ashishb.io.book.shope.Controller;


import com.ashishb.io.book.shope.Model.PurchaseHistory;
import com.ashishb.io.book.shope.Service.IPurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase-history")
public class PurchaseHistoryController {

    @Autowired
    private IPurchaseHistoryService purchaseHistoryService;

    @PostMapping
    public ResponseEntity<?> savePurchaseHistory(@RequestBody PurchaseHistory purchaseHistory){

        return  new ResponseEntity<>(purchaseHistoryService.savePurchaseHistory(purchaseHistory), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllPurchaseOfUser(@PathVariable Long id){

        return  new ResponseEntity<>(purchaseHistoryService.findPurchaseItemsOfUser(id), HttpStatus.OK);

    }
}
