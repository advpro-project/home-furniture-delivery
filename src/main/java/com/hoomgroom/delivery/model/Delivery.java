package com.hoomgroom.delivery.model;

import java.util.UUID;
import java.util.List;

import com.hoomgroom.authentication.buyproduct.model.Furniture;
import com.hoomgroom.authentication.model.User;
import com.hoomgroom.delivery.enums.DeliveryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "deliveries") 
@Getter @Setter
@NoArgsConstructor 
public class Delivery {

    @Id 
    private String kodeResi;

    public void setKoderesi(){
        UUID kodeResiUUID = UUID.randomUUID();
        String kodeResiGenerated = kodeResiUUID.toString();
        this.kodeResi = kodeResiGenerated;
    }

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status = DeliveryStatus.MENUNGGU_VERIFIKASI;

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "transportation_id", referencedColumnName = "id") 
    private Transportation transportation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "delivery_furniture",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "furniture_id"))
    private List<Furniture> furnitureList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Delivery(String kodeResi) {
        this.kodeResi = kodeResi;
    }

    public Delivery(List<Furniture> furnitureList, User user, String kodeResi) {
        this.furnitureList = furnitureList;
        this.user = user;
        this.kodeResi = kodeResi;
    }
}
