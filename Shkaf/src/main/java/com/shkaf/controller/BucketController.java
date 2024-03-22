package com.shkaf.controller;

import com.shkaf.dto.UserDTO;
import com.shkaf.service.BucketService;
import com.shkaf.dto.BucketDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/{userName}")
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
}
