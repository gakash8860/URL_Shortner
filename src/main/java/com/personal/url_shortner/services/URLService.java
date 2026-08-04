package com.personal.url_shortner.services;

import com.personal.url_shortner.dto.UrlMappingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface URLService {

    UrlMappingDTO convertUrl(UrlMappingDTO urlMappingDTO);

    List<UrlMappingDTO> getUrl();
    UrlMappingDTO getUrl(String shortCode);
}
