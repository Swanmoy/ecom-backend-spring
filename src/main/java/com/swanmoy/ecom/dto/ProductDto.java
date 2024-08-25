package com.swanmoy.ecom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swanmoy.ecom.entity.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ProductDto {

    private Long id;
    private String name;
    private Long price;

    private String description;

    private byte[] byteImg;

   private Long categoryId;
   private MultipartFile img;
}
