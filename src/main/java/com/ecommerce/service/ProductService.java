package com.ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.entity.Product;
import com.ecommerce.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> listProducts() {
        return productRepo.findAll();
    }
}
