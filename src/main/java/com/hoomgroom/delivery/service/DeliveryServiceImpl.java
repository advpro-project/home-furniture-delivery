package com.hoomgroom.delivery.service;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        return deliveryRepository.findAll();
    }


    @Override
    public Delivery findByKodeResi(String kodeResi) {
        return deliveryRepository.findByKodeResi(kodeResi);
    }

    @Override
    @Async 
    public CompletableFuture<Delivery> updateStatusAsync(String kodeResi, DeliveryStatus newStatus) {
        Delivery delivery = deliveryRepository.findByKodeResi(kodeResi);
        if (delivery != null) {
            delivery.setStatus(newStatus);
            deliveryRepository.save(delivery);
        }
        return CompletableFuture.completedFuture(delivery);
    }

    @Override
    public Delivery updateTransportation(String kodeResi, Transportation newTransportation) {
        Delivery delivery = deliveryRepository.findByKodeResi(kodeResi);
        if (delivery != null && delivery.getStatus() == DeliveryStatus.DIPROSES) {
            delivery.setTransportation(newTransportation);
            return deliveryRepository.save(delivery);
        }
        return null;
    }

    @Override
    public Delivery deleteDelivery(String kodeResi) {
        if (deliveryRepository.deleteByKodeResi(kodeResi)) {
            return new Delivery(kodeResi);
        }
        return null;
    }
}
