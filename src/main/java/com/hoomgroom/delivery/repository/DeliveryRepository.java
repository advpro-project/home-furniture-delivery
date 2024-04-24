package com.hoomgroom.delivery.repository;

import com.hoomgroom.delivery.model.Delivery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class DeliveryRepository {

    private final List<Delivery> deliveryData;

    public DeliveryRepository() {
        this.deliveryData = new ArrayList<>();
    }

    public Delivery save(Delivery delivery) {
        deliveryData.add(delivery);
        return delivery;
    }

    public Delivery findByKodeResi(String kodeResi) {
        for (Delivery delivery : deliveryData) {
            if (delivery.getKodeResi().equals(kodeResi)) {
                return delivery;
            }
        }
        return null;
    }

    public Iterator<Delivery> findAll() {
        return deliveryData.iterator();
    }

    public boolean delete(String kodeResi) {
        return deliveryData.removeIf(delivery -> delivery.getKodeResi().equals(kodeResi));
    }

    public boolean edit(Delivery updatedDelivery) {
        for (int i = 0; i < deliveryData.size(); i++) {
            Delivery delivery = deliveryData.get(i);
            if (delivery.getKodeResi().equals(updatedDelivery.getKodeResi())) {
                deliveryData.set(i, updatedDelivery);
                return true;
            }
        }
        return false;
    }
}
