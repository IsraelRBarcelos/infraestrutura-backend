package com.agenciacapsula.infraestrutura.application.services;

import com.agenciacapsula.infraestrutura.application.dto.LocationDTO;
import com.agenciacapsula.infraestrutura.domain.model.Photo;
import com.agenciacapsula.infraestrutura.domain.ports.in.RetrievePhotosUseCase;
import com.agenciacapsula.infraestrutura.domain.ports.in.UploadPhotoUseCase;
import com.agenciacapsula.infraestrutura.domain.ports.out.PhotoRepositoryPort;
import com.agenciacapsula.infraestrutura.domain.ports.out.PhotoStoragePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PhotoService implements UploadPhotoUseCase, RetrievePhotosUseCase {

    private final PhotoStoragePort storagePort;
    private final PhotoRepositoryPort repositoryPort;
    private final GeocodingService geocodingService;

    public PhotoService(PhotoStoragePort storagePort, PhotoRepositoryPort repositoryPort, GeocodingService geocodingService) {
        this.storagePort = storagePort;
        this.repositoryPort = repositoryPort;
        this.geocodingService = geocodingService;
    }

    @Override
    @Transactional
    public Photo execute(byte[] fileBytes, String fileName, String description, Double lat, Double lon) {

        LocationDTO location = geocodingService.getLatLongInfo(lat, lon);
        log.info("lat {} lon {} city {} streetName {}", lat, lon, location.address().city(), location.address().road());
        String filePath = storagePort.store(fileBytes, location.address().postcode());
        Photo photoToSave = new Photo(
                null,
                location.address().city(),
                location.address().road(),
                description,
                lat,
                lon,
                filePath,
                LocalDateTime.now()
        );

        return repositoryPort.save(photoToSave);
    }

    @Override
    public List<Photo> getPhotosByCity(String city) {
        return repositoryPort.findByCity(city);
    }

    @Override
    public List<Photo> getPhotosByStreetName(String streetName) {
        return repositoryPort.findByStreetName(streetName);
    }
}