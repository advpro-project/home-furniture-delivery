package com.hoomgroom.delivery.model;
import lombok.Getter;
import lombok.Setter;
import com.hoomgroom.delivery.enums.DeliveryStatus;;

@Getter @Setter
public class Delivery {
    private DeliveryStatus status;
    private Transportation transportation;
    private String kodeResi;

    public Delivery(DeliveryStatus status, Transportation transportation, String kodeResi) {
        this.status = status;
        this.transportation = transportation;
        this.kodeResi = kodeResi;
    }    
}
