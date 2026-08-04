package com.personal.url_shortner.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(  name = "url_mapping",
    indexes = {
            @Index(name = "idx_short_code", columnList = "shortCode"),
    }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 2048)
    private String originalUrl;

    @Column(unique = true, length = 10)
    private String shortCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean isActive;
}
