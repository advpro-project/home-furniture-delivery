package com.hoomgroom.delivery.model;

import java.util.UUID;

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

    public Delivery(String kodeResi) {
        this.kodeResi = kodeResi;
    }
}
