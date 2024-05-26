package com.hoomgroom.delivery.enums;
import lombok.Generated;

public enum DeliveryStatus {
    MENUNGGU_VERIFIKASI ("MENUNGGU_VERIFIKASI"),
    DIPROSES ("DIPROSES"),
    DIKIRIM ("DIKIRIM"),
    TIBA ("TIBA"),
    SELESAI ("SELESAI");

    private final String status;

    @Generated
    private DeliveryStatus(String status){
        this.status = status;
    }
}
