package com.example.hrinterface;

import com.example.hrinterface.property.StorageProperties;
import com.example.hrinterface.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HrInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrInterfaceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileStorageService fileStorageService) {
        return (args) -> {
            fileStorageService.deleteAll();
            fileStorageService.init();
        };
    }
}
