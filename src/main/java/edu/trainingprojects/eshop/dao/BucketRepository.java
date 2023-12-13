package edu.trainingprojects.eshop.dao;

import edu.trainingprojects.eshop.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository <Bucket, Long> {
}
