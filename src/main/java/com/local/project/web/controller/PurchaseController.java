package com.local.project.web.controller;

import com.local.project.domain.Purchase;
import com.local.project.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

   @Autowired
   private PurchaseService purchaseService;

   @GetMapping("")
   public ResponseEntity<List<Purchase>> getAll() {
      return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId) {
      List<Purchase> purchases = purchaseService.getByClient(clientId).orElse(null);
      return purchases != null && !purchases.isEmpty() ? new ResponseEntity<>(purchases, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PostMapping("")
   public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
      return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
   }

}
