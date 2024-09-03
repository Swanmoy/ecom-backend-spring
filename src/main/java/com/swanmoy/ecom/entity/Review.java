package com.swanmoy.ecom.entity;

import com.swanmoy.ecom.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long rating;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;


    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Product product;


    public ReviewDto getDto(){
        ReviewDto reviewDto=new ReviewDto();
        reviewDto.setId(id);
        reviewDto.setDescription(description);
        reviewDto.setRating(rating);
        reviewDto.setUsername(user.getName());
        reviewDto.setUserId(user.getId());
        reviewDto.setProductId(product.getId());
        reviewDto.setReturnedImg(img);
        return reviewDto;
    }
}
