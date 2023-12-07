package com.shubham.swapsie.service;

import com.shubham.swapsie.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product addProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(long id);

    public List<Product> getAllProductsByUserId(long user_id);

    public String deleteProduct(long id);

    public Product updateProduct(long id, Product product);
}
