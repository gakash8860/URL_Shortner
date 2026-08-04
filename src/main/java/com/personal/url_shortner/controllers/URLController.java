package com.personal.url_shortner.controllers;


import com.personal.url_shortner.dto.UrlMappingDTO;
import com.personal.url_shortner.services.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/map")
public class URLController {


    @Autowired
    private URLService urlService;


    @PostMapping("/")
    public ResponseEntity<UrlMappingDTO> convertToShortUrl(@RequestBody UrlMappingDTO urlMappingDTO){

        UrlMappingDTO dto =  urlService.convertUrl(urlMappingDTO);

        return ResponseEntity.status(200).body(dto);
    }
    @GetMapping("/")
    public ResponseEntity<List<UrlMappingDTO>> getUrl(){

        List<UrlMappingDTO> dto =  urlService.getUrl();

        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping
    public ResponseEntity<UrlMappingDTO> getUrl(@RequestParam String shortCode){

        UrlMappingDTO dto =  urlService.getUrl(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(dto.getOriginalUrl()))
                .build();
    }


}
