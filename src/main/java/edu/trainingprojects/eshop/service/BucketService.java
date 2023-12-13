package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.domain.Bucket;
import edu.trainingprojects.eshop.domain.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds );

    void addProduct(Bucket bucket, List<Long> productIds);
}
