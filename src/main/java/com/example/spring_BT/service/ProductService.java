package com.example.spring_BT.service;

import com.example.spring_BT.dtos.ProductDTO;
import com.example.spring_BT.models.Product;
import com.example.spring_BT.repository.ProductRepository;
import com.example.spring_BT.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getNameProduct())
                .description(productDTO.getDescriptionProduct())
                .price(productDTO.getPriceProduct())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productDTO.getNameProduct());
        product.setDescription(productDTO.getDescriptionProduct());
        product.setPrice(productDTO.getPriceProduct());
        return productRepository.save(product);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(Product ->{
            return ProductResponse.fromProduct(Product);
        });
    }
}
