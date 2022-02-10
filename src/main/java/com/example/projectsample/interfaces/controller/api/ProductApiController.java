package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.service.ProductService;
import com.example.projectsample.common.util.aop.ResponseJsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;

    @GetMapping("/")
    @ResponseJsonResult
    public Object getProductList() {
        return productService.getProductList();
    }
}
