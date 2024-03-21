package dashbah.shkaf.service;

import dashbah.shkaf.dto.BucketDTO;
import dashbah.shkaf.model.Bucket;
import dashbah.shkaf.model.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser(String name);
}
