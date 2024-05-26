package com.hoomgroom.delivery.model;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import java.util.List;
import lombok.Generated;

@Generated
public class DeliveryBuilder {
    private DeliveryStatus status = DeliveryStatus.MENUNGGU_VERIFIKASI;
    private Transportation transportation;
    private List<Furniture> furnitureList;
    private String userEmail;

    @Generated
    public DeliveryBuilder status(DeliveryStatus status) {
        this.status = status;
        return this;
    }

    @Generated
    public DeliveryBuilder transportation(Transportation transportation) {
        this.transportation = transportation;
        return this;
    }

    @Generated
    public DeliveryBuilder furnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
        return this;
    }

    @Generated
    public DeliveryBuilder userEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    @Generated
    public Delivery build() {
        return new Delivery(status, transportation, furnitureList, userEmail);
    }
}
