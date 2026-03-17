package com.agenciacapsula.infraestrutura.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO(
        String railway,
        String road,
        String suburb,
        String city,
        String municipality,
        String county,
        @JsonProperty("state_district") String stateDistrict,
        String state,
        @JsonProperty("ISO3166-2-lvl4") String iso3166Lvl4,
        String postcode,
        String country,
        @JsonProperty("country_code") String countryCode
) {}