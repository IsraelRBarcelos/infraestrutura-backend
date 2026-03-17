package com.agenciacapsula.infraestrutura.infrastructure.mapper;

import com.agenciacapsula.infraestrutura.domain.model.Photo;
import com.agenciacapsula.infraestrutura.infrastructure.adapters.out.persistence.PhotoEntity;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class PhotoMapper {
    private static final GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);

    public static PhotoEntity toEntity(Photo photo) {
        Point point = factory.createPoint(new Coordinate(photo.longitude(), photo.latitude()));
        return PhotoEntity.builder()
                .city(photo.city())
                .streetName(photo.streetName())
                .description(photo.description())
                .location(point)
                .storagePath(photo.storagePath())
                .build();
    }

    public static Photo toDomain(PhotoEntity entity) {
        if (entity == null) return null;

        return new Photo(
                entity.getId(),
                entity.getCity(),
                entity.getStreetName(),
                entity.getDescription(),
                entity.getLocation().getY(),
                entity.getLocation().getX(),
                entity.getStoragePath(),
                entity.getCreatedAt()
        );
    }
}