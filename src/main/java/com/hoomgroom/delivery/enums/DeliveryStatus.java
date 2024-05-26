package com.hoomgroom.delivery.enums;

public enum DeliveryStatus {
    MENUNGGU_VERIFIKASI ("MENUNGGU_VERIFIKASI"),
    DIPROSES ("DIPROSES"),
    DIKIRIM ("DIKIRIM"),
    TIBA ("TIBA"),
    SELESAI ("SELESAI");

    private final String status;

    private DeliveryStatus(String status){
        this.status = status;
    }
}
