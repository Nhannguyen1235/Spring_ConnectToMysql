package com.example.spring_BT.repository;

import com.example.spring_BT.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
