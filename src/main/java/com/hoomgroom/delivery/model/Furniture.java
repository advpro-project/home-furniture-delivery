package com.hoomgroom.delivery.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "furnitures")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    private UUID id;
    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private int soldQuantity;
    private double originalPrice;
    private double discountedPrice;
    private boolean hasDiscount;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}