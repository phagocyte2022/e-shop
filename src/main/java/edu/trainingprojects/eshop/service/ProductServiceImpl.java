package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.dao.ProductRepository;
import edu.trainingprojects.eshop.domain.Bucket;
import edu.trainingprojects.eshop.domain.Product;
import edu.trainingprojects.eshop.domain.User;
import edu.trainingprojects.eshop.dto.ProductDTO;
import edu.trainingprojects.eshop.mapper.ProductMapper;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService,
                              BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @javax.transaction.Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if(user == null){
            throw new RuntimeException("User not found. " + username);
        }

        Bucket bucket = user.getBucket();
        if(bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        }
        else {
            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO dto) {
        Product product = mapper.toProduct(dto);
        Product savedProduct = productRepository.save(product);


    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return ProductMapper.MAPPER.fromProduct(product);
    }
}