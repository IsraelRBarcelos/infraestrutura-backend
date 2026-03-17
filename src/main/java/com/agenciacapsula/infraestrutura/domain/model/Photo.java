package com.agenciacapsula.infraestrutura.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Photo(
        UUID id,
        String city,
        String streetName,
        String description,
        Double latitude,
        Double longitude,
        String storagePath,
        LocalDateTime createdAt
) {
    public Photo {
        if (latitude < -90 || latitude > 90) throw new IllegalArgumentException("Latitude inválida");
        if (longitude < -180 || longitude > 180) throw new IllegalArgumentException("Longitude inválida");
    }
}