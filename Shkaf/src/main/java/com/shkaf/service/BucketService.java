package com.shkaf.service;

import com.shkaf.model.Bucket;
import com.shkaf.model.User;
import com.shkaf.dto.BucketDTO;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser(String name);
}
