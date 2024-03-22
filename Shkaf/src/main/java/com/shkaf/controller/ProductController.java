package com.shkaf.controller;

import com.shkaf.dto.ProductDTO;
import com.shkaf.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> list() {
        List<ProductDTO> products = productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/bucket")
    public ResponseEntity<String> addBucket(@RequestParam String userName, @RequestParam Long productId) {
        try {
            productService.addToUserBucket(productId, userName);
            return ResponseEntity.ok("Product added");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
