package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.dao.BucketRepository;
import edu.trainingprojects.eshop.dao.ProductRepository;
import edu.trainingprojects.eshop.domain.Bucket;
import edu.trainingprojects.eshop.domain.Product;
import edu.trainingprojects.eshop.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);

    }


}
