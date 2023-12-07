package com.shubham.swapsie.repository;

import com.shubham.swapsie.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findAllByUserId(long user_id);
}
