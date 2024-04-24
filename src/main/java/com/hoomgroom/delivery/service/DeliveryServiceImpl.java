package com.hoomgroom.delivery.service;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAllDeliveries() {
        Iterator<Delivery> deliveryIterator = deliveryRepository.findAll();
        List<Delivery> allDeliveries = new ArrayList<>();
        deliveryIterator.forEachRemaining(allDeliveries::add);
        return allDeliveries;
    }

    @Override
    public Delivery findByKodeResi(String kodeResi) {
        return deliveryRepository.findByKodeResi(kodeResi);
    }

    @Override
    public Delivery updateStatus(String kodeResi, DeliveryStatus newStatus) {
        Delivery delivery = deliveryRepository.findByKodeResi(kodeResi);
        if (delivery != null) {
            delivery.setStatus(newStatus);
            if (deliveryRepository.edit(delivery)) {
                return delivery;
            }
        }
        return null;
    }

    @Override
    public Delivery updateTransportation(String kodeResi, Transportation newTransportation) {
        Delivery delivery = deliveryRepository.findByKodeResi(kodeResi);
        if (delivery != null && delivery.getStatus() == DeliveryStatus.DIPROSES) {
            delivery.setTransportation(newTransportation);
            if (deliveryRepository.edit(delivery)) {
                return delivery;
            }
        }
        return null;
    }

    @Override
    public Delivery deleteDelivery(String kodeResi) {
        if (deliveryRepository.delete(kodeResi)) {
            return new Delivery(kodeResi);
        }
        return null;
    }
}
