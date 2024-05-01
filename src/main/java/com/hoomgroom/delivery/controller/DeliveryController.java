package com.hoomgroom.delivery.controller;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public Delivery createDelivery(Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.findAllDeliveries();
    }

    @GetMapping("/{kodeResi}")
    public Delivery getDeliveryByKodeResi(String kodeResi) {
        return deliveryService.findByKodeResi(kodeResi);
    }

    @PutMapping("/{kodeResi}/status")
    public Delivery updateDeliveryStatus(String kodeResi,
                                         DeliveryStatus newStatus) {
        return deliveryService.updateStatus(kodeResi, newStatus);
    }

    @PutMapping("/{kodeResi}/transportation")
    public Delivery updateDeliveryTransportation(String kodeResi,
                                                 Transportation newTransportation) {
        return deliveryService.updateTransportation(kodeResi, newTransportation);
    }

    @DeleteMapping("/{kodeResi}")
    public void deleteDelivery(String kodeResi) {
        deliveryService.deleteDelivery(kodeResi);
    }
}
