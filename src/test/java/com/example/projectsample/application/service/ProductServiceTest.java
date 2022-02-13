package com.example.projectsample.application.service;

import com.example.projectsample.application.model.dto.ProductResponseDto;
import com.example.projectsample.application.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void 상품목록_테스트() {
        List<ProductResponseDto> productList = productService.getProductList();
        assertThat(productList).isNotNull();
    }

}