package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.dao.ProductRepository;
import edu.trainingprojects.eshop.dto.ProductDTO;
import edu.trainingprojects.eshop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;

    }


    @Override
    public List<ProductDTO> getAll() {
        return null;
    }
}
