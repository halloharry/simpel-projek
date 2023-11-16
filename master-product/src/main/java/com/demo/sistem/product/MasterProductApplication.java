package com.demo.sistem.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.photo.dao",
        "com.photo.dto",
        "com.photo.model",
        "com.photo.util",
        "com.demo.sistem.product"
}, lazyInit = true)
@EnableJpaRepositories(basePackages = {
        "com.photo.dao"})
@EntityScan(basePackages = "com.photo.model")
public class MasterProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterProductApplication.class, args);
    }


}
