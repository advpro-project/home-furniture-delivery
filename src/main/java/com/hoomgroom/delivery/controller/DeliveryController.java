package com.hoomgroom.delivery.controller;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.findAllDeliveries();
    }

    @GetMapping("/{kodeResi}")
    public Delivery getDeliveryByKodeResi(@PathVariable String kodeResi) {
        return deliveryService.findByKodeResi(kodeResi);
    }

    @PutMapping("/{kodeResi}/status")
    public CompletableFuture<Delivery> updateDeliveryStatus(@PathVariable String kodeResi,
                                         @RequestParam DeliveryStatus newStatus) {
        return deliveryService.updateStatusAsync(kodeResi, newStatus);
    }

    @PutMapping("/{kodeResi}/transportation")
    public Delivery updateDeliveryTransportation(@PathVariable String kodeResi,
                                                 @RequestBody Transportation newTransportation) {
        return deliveryService.updateTransportation(kodeResi, newTransportation);
    }

    @DeleteMapping("/{kodeResi}")
    public void deleteDelivery(@PathVariable String kodeResi) {
        deliveryService.deleteDelivery(kodeResi);
    }
}
