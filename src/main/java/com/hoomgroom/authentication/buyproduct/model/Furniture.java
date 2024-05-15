package com.hoomgroom.authentication.buyproduct.model;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter

public class Furniture {
    private UUID id;
    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private double originalPrice;
    private double discountedPrice;
    private boolean hasDiscount;
}
