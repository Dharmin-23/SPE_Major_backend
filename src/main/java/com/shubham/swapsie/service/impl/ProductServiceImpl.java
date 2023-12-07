package com.shubham.swapsie.service.impl;

import com.shubham.swapsie.exception.ResourceNotFoundException;
import com.shubham.swapsie.model.Product;
import com.shubham.swapsie.model.User;
import com.shubham.swapsie.repository.ProductRepository;
import com.shubham.swapsie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Product with id : "+id+" does not exist"));
        return product;
    }

    @Override
    public List<Product> getAllProductsByUserId(long user_id) {
        List<Product> productList = productRepository.findAllByUserId(user_id);
        return productList;
    }

    @Override
    public String deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Product with id : "+id+" does not exist"));
        productRepository.delete(product);
        return "Product "+product.getName()+" with id : "+product.getId()+" deleted successfully";
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product updatedProduct = productRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Product with id : "+id+" does not exist"));
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setImgLink(product.getImgLink());
        productRepository.save(updatedProduct);
        return updatedProduct;
    }

}
