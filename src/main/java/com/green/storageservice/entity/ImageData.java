package com.green.storageservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
}
