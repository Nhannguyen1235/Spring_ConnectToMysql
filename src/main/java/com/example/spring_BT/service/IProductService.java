package com.example.spring_BT.service;

import com.example.spring_BT.dtos.ProductDTO;
import com.example.spring_BT.models.Product;
import com.example.spring_BT.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
   Product createProduct(ProductDTO productDTO);
   Product getProduct(Long id);
   Product deleteProduct(Long id);
   Product updateProduct(Long id, ProductDTO productDTO);
   List<Product> getAllProducts();
   Page<ProductResponse> getAllProducts(PageRequest pageRequest);
}
