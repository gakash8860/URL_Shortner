package com.personal.url_shortner.services;

import com.personal.url_shortner.dto.UrlMappingDTO;
import com.personal.url_shortner.exception.DataAlreadyPresent;
import com.personal.url_shortner.exception.NoDataFound;
import com.personal.url_shortner.models.UrlMapping;
import com.personal.url_shortner.repo.URL_Repo;
import com.personal.url_shortner.transformers.UrlTransformer;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;


import static com.personal.url_shortner.Utils.Utility.BASE62;
import static com.personal.url_shortner.Utils.Utility.BASE_URL;

@Service
public class URLServiceImpl implements URLService{

    private static final SecureRandom RANDOM = new SecureRandom();
    private final URL_Repo repo;
    public URLServiceImpl(URL_Repo repo){
        this.repo = repo;
    }


    @Override
    public UrlMappingDTO convertUrl(UrlMappingDTO urlMappingDTO) {

        Boolean isAvailable = repo.existsByOriginalUrl(urlMappingDTO.getOriginalUrl());
        if(isAvailable) { throw new DataAlreadyPresent("URL Already Presnt");}
        String shortKey = generateUniqueShortCode();
        UrlMapping mapping = UrlTransformer.entityFromDTO(urlMappingDTO,shortKey);
        mapping = repo.save(mapping);
        UrlMappingDTO mappingDTO = UrlTransformer.dtoFromEntity(mapping);
        mappingDTO.setShortCode(BASE_URL + shortKey);
        return mappingDTO;
    }

    @Override
    public List<UrlMappingDTO> getUrl() {
        List<UrlMapping> urlMappingList = repo.findAll();
        return urlMappingList.stream()
                .map(UrlTransformer::dtoFromEntity).toList();
    }

    @Override
    public UrlMappingDTO getUrl(String shortCode) {
        UrlMapping url = repo.findbyShortCode(shortCode);
        if (Objects.isNull(url)){
            throw new NoDataFound("Given Code is not available..");
        }
        return  UrlTransformer.dtoFromEntity(url);
    }


    private  String convertToBase62() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(BASE62.charAt(RANDOM.nextInt(BASE62.length())));
        }
        return sb.toString();
    }

    private String generateUniqueShortCode() {
        String code;

        do {
            code = convertToBase62();
        } while (repo.existsByShortCode(code));

        return code;
    }
}
