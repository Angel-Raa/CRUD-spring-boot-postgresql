package org.caja.ideal.shoppingcart.controller;

import org.caja.ideal.shoppingcart.api.ApiResponse;
import org.caja.ideal.shoppingcart.models.Product;
import org.caja.ideal.shoppingcart.service.ProductService;
import org.caja.ideal.shoppingcart.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok((service.getAllProduct()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(service.getProduct(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> postProduct(@RequestBody Product body){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProduct(body));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody Product body){
        return ResponseEntity.ok(service.updateProduct(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        ApiResponse api = ApiResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .http(HttpStatus.NO_CONTENT)
                .message(Message.SUCCESSFULLY_REMOVED)
                .build();
        service.deleteProduct(id);
        return ResponseEntity.ok(api);
    }
}
