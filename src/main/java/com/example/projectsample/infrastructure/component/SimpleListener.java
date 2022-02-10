package com.example.projectsample.infrastructure.component;

import com.example.projectsample.application.model.entity.Product;
import com.example.projectsample.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class SimpleListener {

    private final ProductRepository productRepository;

    @EventListener({ContextRefreshedEvent.class})
    public void contextRefreshedEvent() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("사과").price(2000).build());
        products.add(Product.builder().name("바나나").price(1000).build());
        products.add(Product.builder().name("생수").price(3000).build());
        products.add(Product.builder().name("우유").price(2000).build());
        products.add(Product.builder().name("물티슈").price(5000).build());
        products.add(Product.builder().name("키보드").price(6000).build());
        products.add(Product.builder().name("마우스").price(10000).build());
        products.add(Product.builder().name("가습기").price(3000).build());
        products.add(Product.builder().name("이어폰").price(5000).build());
        products.add(Product.builder().name("냉장고").price(10000).build());
        productRepository.saveAll(products);
    }
}