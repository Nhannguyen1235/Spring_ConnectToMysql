package com.example.spring_BT.controllers;

import com.example.spring_BT.dtos.ProductDTO;
import com.example.spring_BT.models.Product;
import com.example.spring_BT.response.ProductListResponse;
import com.example.spring_BT.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import com.example.spring_BT.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }
    @PostMapping("/create")
    public String createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return errors.toString();
        }
        return "Product created successfully" + productService.createProduct(productDTO);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return "Product updated successfully" + productService.updateProduct(id, productDTO);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Product deleted successfully" + productService.deleteProduct(id);
    }
    @GetMapping("/list")
    public ResponseEntity<ProductListResponse> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit,Sort.by("createdAt").descending());
        Page<ProductResponse> productResponsePage = productService.getAllProducts(pageRequest);
        int totalPage = productResponsePage.getTotalPages();
        List<ProductResponse> responseProducts = productResponsePage.getContent();
        return ResponseEntity.ok(ProductListResponse.builder()
                .products(responseProducts)
                .totalPage(totalPage)
                .build()
        );
    }
}
