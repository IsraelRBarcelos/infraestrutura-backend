package com.agenciacapsula.infraestrutura.domain.ports.in;

import com.agenciacapsula.infraestrutura.domain.model.Photo;

public interface UploadPhotoUseCase {
    Photo execute(byte[] fileBytes, String fileName, String description, Double lat, Double lon);
}