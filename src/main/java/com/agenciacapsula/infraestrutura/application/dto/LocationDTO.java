package com.agenciacapsula.infraestrutura.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDTO(
        @JsonProperty("place_id") Long placeId,
        String licence,
        @JsonProperty("osm_type") String osmType,
        @JsonProperty("osm_id") Long osmId,
        String lat,
        String lon,
        @JsonProperty("class") String locationClass,
        String type,
        @JsonProperty("place_rank") Integer placeRank,
        Double importance,
        String addresstype,
        String name,
        @JsonProperty("display_name") String displayName,
        AddressDTO address,
        List<String> boundingbox
) {}