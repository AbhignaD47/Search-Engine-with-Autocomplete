package com.example;
import com.example.model.Product;
import com.example.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SearchEngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchEngineApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(ProductService productService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            List<Product> products = Arrays.asList(mapper.readValue(new File("src/main/resources/data.json"), Product[].class));
            productService.indexProducts(products);
        };
    }
}
