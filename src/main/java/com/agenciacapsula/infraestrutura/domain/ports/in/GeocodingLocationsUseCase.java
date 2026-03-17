package com.agenciacapsula.infraestrutura.domain.ports.in;

import com.agenciacapsula.infraestrutura.application.dto.LocationDTO;

public interface GeocodingLocationsUseCase {
    LocationDTO getLatLongInfo(Double lat, Double lon);
}
