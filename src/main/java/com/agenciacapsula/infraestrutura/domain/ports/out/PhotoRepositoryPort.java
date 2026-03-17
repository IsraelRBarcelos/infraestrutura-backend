package com.agenciacapsula.infraestrutura.domain.ports.out;

import com.agenciacapsula.infraestrutura.domain.model.Photo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhotoRepositoryPort {
    Photo save(Photo photo);
    Optional<Photo> findById(UUID id);
    List<Photo> findAll();
    List<Photo> findByCity(String city);
    List<Photo> findByStreetName(String streetName);
}