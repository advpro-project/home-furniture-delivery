package com.hoomgroom.delivery.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Generated;

@Entity
@NoArgsConstructor
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Generated
    public Transportation(String type) {
        this.type = type;
    }

    @Generated
    public Long getId() {
        return id;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public String getType() {
        return type;
    }

    @Generated
    public void setType(String type) {
        this.type = type;
    }
}
