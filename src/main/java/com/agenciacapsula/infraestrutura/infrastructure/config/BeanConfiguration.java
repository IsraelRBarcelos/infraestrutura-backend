package com.agenciacapsula.infraestrutura.infrastructure.config;


import com.agenciacapsula.infraestrutura.application.services.GeocodingService;
import com.agenciacapsula.infraestrutura.application.services.PhotoService;
import com.agenciacapsula.infraestrutura.domain.ports.out.PhotoRepositoryPort;
import com.agenciacapsula.infraestrutura.domain.ports.out.PhotoStoragePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PhotoService photoService(PhotoStoragePort storagePort,
                                     PhotoRepositoryPort repositoryPort,
                                     GeocodingService geocodingService) {
        return new PhotoService(storagePort,repositoryPort,geocodingService);
    }
}