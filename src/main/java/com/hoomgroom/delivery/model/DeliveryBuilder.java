package com.hoomgroom.delivery.model;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import java.util.List;

public class DeliveryBuilder {
    private DeliveryStatus status = DeliveryStatus.MENUNGGU_VERIFIKASI;
    private Transportation transportation;
    private List<Furniture> furnitureList;
    private User user;

    public DeliveryBuilder status(DeliveryStatus status) {
        this.status = status;
        return this;
    }

    public DeliveryBuilder transportation(Transportation transportation) {
        this.transportation = transportation;
        return this;
    }

    public DeliveryBuilder furnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
        return this;
    }

    public DeliveryBuilder user(User user) {
        this.user = user;
        return this;
    }

    public Delivery build() {
        return new Delivery(status, transportation, furnitureList, user);
    }
}
