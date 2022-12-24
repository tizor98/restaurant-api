package com.local.project.web.controller;

import com.local.project.domain.Product;
import com.local.project.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

   @Autowired
   private ProductService productService;

   @GetMapping("")
   @ApiOperation("Get all supermarket products")
   public ResponseEntity<List<Product>> getAll() {
      return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
   }

   @GetMapping("/{id}")
   @ApiOperation("Get the product indicated in the id")
   @ApiResponses({
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 404, message = "Product not found")
   })
   public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {
      return ResponseEntity.of(productService.getProduct(productId));
   }

   @GetMapping("/categories/{id}")
   public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int categoryId) {
      List<Product> products = productService.getByCategory(categoryId).orElse(null);
      return products != null && !products.isEmpty() ? new ResponseEntity<>(products, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PostMapping("")
   public ResponseEntity<Product> save(@RequestBody Product product) {
      return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity delete(@PathVariable("id") int productId) {
      return new ResponseEntity(productService.delete(productId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
   }

}
