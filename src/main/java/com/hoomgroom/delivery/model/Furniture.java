package com.hoomgroom.delivery.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.Generated;

@Entity
@Getter @Setter
@SuppressWarnings("unused")
@Generated
public class Furniture {
    @Id
    private UUID id;

    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private double originalPrice;
    private double discountedPrice;
    private boolean hasDiscount;
}
