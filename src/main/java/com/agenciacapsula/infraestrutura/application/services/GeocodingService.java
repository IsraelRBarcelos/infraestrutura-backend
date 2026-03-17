package com.agenciacapsula.infraestrutura.application.services;

import com.agenciacapsula.infraestrutura.application.dto.LocationDTO;
import com.agenciacapsula.infraestrutura.domain.ports.in.GeocodingLocationsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@Slf4j
public class GeocodingService implements GeocodingLocationsUseCase {

    private final WebClient webClient;

    public GeocodingService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public LocationDTO getLatLongInfo(Double lat, Double lon) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/reverse")
                        .queryParam("format", "json")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .build())
                .retrieve()
                .bodyToMono(LocationDTO.class)
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(2)))
                .onErrorResume(e -> {
                    log.warn("Falha ao obter localização após tentativas: {}", e.getMessage());
                    return Mono.empty();
                })
                .block();
    }
}
