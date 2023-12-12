package edu.trainingprojects.eshop.dao;

import edu.trainingprojects.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long>{

}
