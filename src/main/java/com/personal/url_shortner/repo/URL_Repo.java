package com.personal.url_shortner.repo;

import com.personal.url_shortner.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface URL_Repo extends JpaRepository<UrlMapping,Long> {

    Boolean existsByOriginalUrl(String originalUrl);

    @Query(value = "select * from url_mapping where short_code =:shortCode",nativeQuery = true)
    UrlMapping findbyShortCode(String shortCode);

    Boolean existsByShortCode(String code);
}
