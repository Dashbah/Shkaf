package dashbah.shkaf.service;

import dashbah.shkaf.dao.BucketRepository;
import dashbah.shkaf.dao.ProductRepository;
import dashbah.shkaf.model.Bucket;
import dashbah.shkaf.model.Product;
import dashbah.shkaf.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;


    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                // getOne gets one ref to object, findById - gets object
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }
}
