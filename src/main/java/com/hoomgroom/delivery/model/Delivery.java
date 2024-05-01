package com.hoomgroom.delivery.model;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Delivery {
    private DeliveryStatus status = DeliveryStatus.MENUNGGU_VERIFIKASI;
    private Transportation transportation;
    private String kodeResi;

    public Delivery(String kodeResi) {
        this.kodeResi = kodeResi;
    }
}
