package com.agenciacapsula.infraestrutura.infrastructure.adapters.out.persistence;

import com.agenciacapsula.infraestrutura.domain.model.Photo;
import com.agenciacapsula.infraestrutura.domain.ports.out.PhotoRepositoryPort;
import com.agenciacapsula.infraestrutura.infrastructure.mapper.PhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PhotoRepositoryAdapter implements PhotoRepositoryPort {

    private final PhotoJpaRepository jpaRepository;

    @Override
    public Photo save(Photo photo) {
        PhotoEntity entity = PhotoMapper.toEntity(photo);
        PhotoEntity savedEntity = jpaRepository.save(entity);
        return PhotoMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Photo> findById(UUID id) {
        Optional<PhotoEntity> photoEntity = jpaRepository.findById(id);
        return Optional.of(PhotoMapper.toDomain(photoEntity.orElseThrow()));
    }

    @Override
    public List<Photo> findAll() {
        return jpaRepository.findAll().stream().map(PhotoMapper::toDomain).toList();
    }

    @Override
    public List<Photo> findByCity(String city) {
        return jpaRepository.findByCity(city).stream().map(PhotoMapper::toDomain).toList();
    }

    @Override
    public List<Photo> findByStreetName(String streetName) {
        return jpaRepository.findByStreetName(streetName).stream().map(PhotoMapper::toDomain).toList();
    }
}