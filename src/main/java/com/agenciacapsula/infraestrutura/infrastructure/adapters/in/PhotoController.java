package com.agenciacapsula.infraestrutura.infrastructure.adapters.in;

import com.agenciacapsula.infraestrutura.domain.model.Photo;
import com.agenciacapsula.infraestrutura.domain.ports.in.RetrievePhotosUseCase;
import com.agenciacapsula.infraestrutura.domain.ports.in.UploadPhotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final UploadPhotoUseCase uploadPhotoUseCase;
    private final RetrievePhotosUseCase retrievePhotosUseCase;

    @PostMapping("/upload")
    public ResponseEntity<Photo> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String description,
            @RequestParam("lat") Double lat,
            @RequestParam("lon") Double lon
    ) throws IOException {


        Photo photo = uploadPhotoUseCase.execute(
                file.getBytes(),
                file.getOriginalFilename(),
                description,
                lat,
                lon
        );

        return ResponseEntity.ok(photo);
    }

    @GetMapping("/city-gallery")
    public ResponseEntity<Map<String, List<Photo>>> getGalleryByCity(@RequestParam String city) {
        List<Photo> allPhotos = retrievePhotosUseCase.getPhotosByCity(city);

        Map<String, List<Photo>> groupedPhotos = allPhotos.stream()
                .collect(Collectors.groupingBy(Photo::streetName));

        return ResponseEntity.ok(groupedPhotos);
    }

    @GetMapping("/city-gallery/street")
    public ResponseEntity<List<Photo>> getGalleryByCityStreet(@RequestParam String streetName) {
        List<Photo> allPhotos = retrievePhotosUseCase.getPhotosByStreetName(streetName);
        return ResponseEntity.ok(allPhotos);
    }
}