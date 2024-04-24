package com.hoomgroom.delivery.service;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;

import java.util.List;

public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);
    List<Delivery> findAllDeliveries();
    Delivery findByKodeResi(String kodeResi);
    Delivery updateStatus(String kodeResi, DeliveryStatus newStatus);
    Delivery updateTransportation(String kodeResi, Transportation newTransportation);
    Delivery deleteDelivery(String kodeResi);
}
