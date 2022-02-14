package com.example.projectsample.infrastructure.component;

import com.example.projectsample.application.model.entity.Member;
import com.example.projectsample.application.model.entity.Product;
import com.example.projectsample.application.repository.MemberRepository;
import com.example.projectsample.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class SimpleListener {

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

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
        products.add(Product.builder().name("타이어").price(12000).build());
        products.add(Product.builder().name("칫솔").price(1500).build());
        products.add(Product.builder().name("건전지").price(1000).build());
        products.add(Product.builder().name("귤").price(500).build());
        products.add(Product.builder().name("계란").price(6000).build());
        products.add(Product.builder().name("배추").price(3000).build());
        products.add(Product.builder().name("오이").price(2500).build());
        products.add(Product.builder().name("감자").price(7000).build());
        products.add(Product.builder().name("양파").price(12000).build());
        products.add(Product.builder().name("두부").price(1300).build());
        productRepository.saveAll(products);

        String id = "admin";
        String password = "admin";
        Member member = Member.builder()
                .customMemberId("admin")
                .password(passwordEncoder.encode(password))
                .build();

        memberRepository.save(member);
    }
}