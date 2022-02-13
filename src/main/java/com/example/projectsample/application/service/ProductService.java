package com.example.projectsample.application.service;

import com.example.projectsample.application.model.dto.ProductResponseDto;
import com.example.projectsample.application.model.entity.Product;
import com.example.projectsample.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> getProductList() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> response = new ArrayList<>();
        for (Product product : products) {
            response.add(ProductResponseDto.builder()
                    .name(product.getName())
                    .price(product.getPrice()).build());
        }
        return response;
    }
}
