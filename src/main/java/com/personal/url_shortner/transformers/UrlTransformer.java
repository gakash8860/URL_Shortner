package com.personal.url_shortner.transformers;

import com.personal.url_shortner.dto.UrlMappingDTO;
import com.personal.url_shortner.models.UrlMapping;

import java.time.LocalDateTime;

import static com.personal.url_shortner.Utils.Utility.BASE_URL;

public class UrlTransformer {

    public static UrlMapping entityFromDTO(UrlMappingDTO dto, String shortCode){
        return UrlMapping.builder()
                .originalUrl(dto.getOriginalUrl())
                .shortCode(shortCode)
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();
    }

    public static UrlMappingDTO dtoFromEntity(UrlMapping mapping){
        return UrlMappingDTO.builder()
                .originalUrl(mapping.getOriginalUrl())
                .shortCode(BASE_URL + mapping.getShortCode())
                .createdAt(LocalDateTime.now())
                .isActive(mapping.isActive())
                .build();
    }
}
