package com.shkaf.controller;

import com.shkaf.dto.UserDTO;
import com.shkaf.service.BucketService;
import com.shkaf.dto.BucketDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PreAuthorize("authentication.principal.username == #userName")
    @GetMapping("/my-bucket/{userName}")
    public ResponseEntity<BucketDTO> getBucket(@PathVariable String userName) {
        if (userName == null) {
            // If not authenticated, return 403 Forbidden
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            // If authenticated, retrieve the bucket for the user
            BucketDTO bucketDto = bucketService.getBucketByUser(userName);
            return ResponseEntity.ok(bucketDto);
        }
    }

    @PreAuthorize("authentication.principal.username == #userDTO.username")
    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addProductToBucket(
            @PathVariable Long productId, @RequestBody UserDTO userDTO) {
        // Реализация добавления продукта в корзину
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PreAuthorize("authentication.principal.username == #userDTO.username")
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromBucket(
            @PathVariable Long productId, @RequestBody UserDTO userDTO) {
        // Реализация удаления продукта из корзины
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
