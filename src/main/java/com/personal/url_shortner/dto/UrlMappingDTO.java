package com.personal.url_shortner.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlMappingDTO {


    private String shortCode;
    private String originalUrl;
    private LocalDateTime createdAt;
    private Boolean isActive;



}
