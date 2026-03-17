package com.agenciacapsula.infraestrutura.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoJpaRepository extends JpaRepository<PhotoEntity, UUID> {

    @Query(value = "SELECT * FROM photos p WHERE ST_DWithin(p.location, ST_SetSRID(ST_Point(:lon, :lat), 4326), :distance)",
            nativeQuery = true)
    List<PhotoEntity> findByLocationNear(@Param("lat") Double lat,
                                         @Param("lon") Double lon,
                                         @Param("distance") Double distance);

    List<PhotoEntity> findByStreetName(String streetName);

    List<PhotoEntity> findByCity(String city);
}