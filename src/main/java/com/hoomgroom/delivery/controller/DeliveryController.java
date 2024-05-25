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

    @PostMapping("/delivery/create")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @GetMapping("/getAllDeliv")
    public List<Delivery> getAllDeliveries() {
        return deliveryService.findAllDeliveries();
    }

    @GetMapping("/get{kodeResi}")
    public Delivery getDeliveryByKodeResi(@PathVariable String kodeResi) {
        return deliveryService.findByKodeResi(kodeResi);
    }

    @PutMapping("/updateStatus{kodeResi}/status")
    public CompletableFuture<Delivery> updateDeliveryStatus(@PathVariable String kodeResi,
                                         @RequestParam DeliveryStatus newStatus) {
        return deliveryService.updateStatusAsync(kodeResi, newStatus);
    }

    @PutMapping("/updateTransportation{kodeResi}/transportation")
    public Delivery updateDeliveryTransportation(@PathVariable String kodeResi,
                                                 @RequestBody Transportation newTransportation) {
        return deliveryService.updateTransportation(kodeResi, newTransportation);
    }

    @DeleteMapping("/delete{kodeResi}")
    public void deleteDelivery(@PathVariable String kodeResi) {
        deliveryService.deleteDelivery(kodeResi);
    }
}
