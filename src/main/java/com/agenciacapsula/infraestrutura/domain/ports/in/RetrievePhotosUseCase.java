package com.agenciacapsula.infraestrutura.domain.ports.in;

import com.agenciacapsula.infraestrutura.domain.model.Photo;

import java.util.List;

public interface RetrievePhotosUseCase {
    List<Photo> getPhotosByCity(String city);
    List<Photo> getPhotosByStreetName(String streetName);
    List<Photo> getAll();
}
