package com.hoomgroom.delivery.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Getter @Setter
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private double originalPrice;
    private double discountedPrice;
    private boolean hasDiscount;
}
