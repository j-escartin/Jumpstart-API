package com.aceadoratech.jumpstart.controller;


import com.aceadoratech.jumpstart.entity.Transaction;

import com.aceadoratech.jumpstart.config.JwtService;
import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.entity.UserDetail;
import com.aceadoratech.jumpstart.entity.UserLogin;

import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.service.ProductService;
import com.aceadoratech.jumpstart.service.TransactionService;
import com.aceadoratech.jumpstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/base")
@RequiredArgsConstructor
public class BaseController {

    private final TransactionService transactionService;

    

    private final TransactionService transactionService;
    private final ProductService productService;
    private final JwtService jwtService;
    private final UserService userService;
  
    @PostMapping("/transaction")
    public ResponseEntity<String> postTransaction(@RequestBody TransactionalRequest transactionalRequest) {

        // Call the transactionService to create the transaction
        boolean created = transactionService.createTransaction(transactionalRequest);

        // Check if the transaction was created successfully
        if (created) {
            // Return a successful response with an OK status code and success message
            return ResponseEntity.ok().body("Successfully ordered the product");
        } else {
            // Return an error response with an Internal Server Error status code and error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create transaction");
        }
    }
    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return transactionService.getTransaction(id);
    }
  
    @GetMapping("/product")
    public List<Product> getAllProduct() {
        // Retrieve all products using the productService
        return productService.getAll();
    }
    @GetMapping("/user/{token}")
    public UserLogin getSingleUser(@PathVariable String token){
        String email = jwtService.extractUsername(token);
        return userService.getSingleUser(email);
    }

}
