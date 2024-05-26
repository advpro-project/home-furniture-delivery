package com.hoomgroom.delivery.model;

import java.util.UUID;
import java.util.List;
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

    @PrePersist
    public void setKodeResi() {
        UUID kodeResiUUID = UUID.randomUUID();
        String kodeResiGenerated = "HG-" + kodeResiUUID.toString();
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

    @Column(name = "user_email")
    private String userEmail;

    public Delivery(DeliveryStatus status, Transportation transportation, List<Furniture> furnitureList, String userEmail) {
        this.setKodeResi();
        this.status = status;
        this.transportation = transportation;
        this.furnitureList = furnitureList;
        this.userEmail = userEmail;
    }

    public Delivery(String kodeResi) {
        this.kodeResi = kodeResi;
    }
}
