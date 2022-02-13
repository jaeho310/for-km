package com.example.projectsample.interfaces.controller.api;

import com.example.projectsample.application.service.ProductService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(ProductApiController.class)
class ProductApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    Gson gson;

    @BeforeEach
    void setup() {
        this.gson = new Gson();
    }

    @Test
    void getProducts() throws Exception {
        mvc.perform(get("/api/products")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}