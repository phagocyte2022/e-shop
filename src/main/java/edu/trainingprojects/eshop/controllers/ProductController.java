package edu.trainingprojects.eshop.controllers;

import edu.trainingprojects.eshop.domain.Product;
import edu.trainingprojects.eshop.dto.ProductDTO;
import edu.trainingprojects.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model){
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }


}
