package com.hoomgroom.delivery.controller;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDelivery() {
        Delivery delivery = new Delivery("123");
        when(deliveryService.createDelivery(delivery)).thenReturn(delivery);

        Delivery createdDelivery = deliveryController.createDelivery(delivery);

        assertEquals(delivery, createdDelivery);
        verify(deliveryService, times(1)).createDelivery(delivery);
    }

    @Test
    void testGetAllDeliveries() {
        Delivery delivery1 = new Delivery("123");
        Delivery delivery2 = new Delivery("456");
        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2);
        when(deliveryService.findAllDeliveries()).thenReturn(deliveries);

        List<Delivery> retrievedDeliveries = deliveryController.getAllDeliveries();

        assertEquals(deliveries, retrievedDeliveries);
        verify(deliveryService, times(1)).findAllDeliveries();
    }

    @Test
    void testGetDeliveryByKodeResi() {
        Delivery delivery = new Delivery("123");
        when(deliveryService.findByKodeResi("123")).thenReturn(delivery);

        Delivery retrievedDelivery = deliveryController.getDeliveryByKodeResi("123");

        assertEquals(delivery, retrievedDelivery);
        verify(deliveryService, times(1)).findByKodeResi("123");
    }

    @Test
    void testGetDeliveryByKodeResiNotFound() {
        when(deliveryService.findByKodeResi("123")).thenReturn(null);

        Delivery retrievedDelivery = deliveryController.getDeliveryByKodeResi("123");

        assertNull(retrievedDelivery);
        verify(deliveryService, times(1)).findByKodeResi("123");
    }

    @Test
    void testUpdateDeliveryStatus() throws Exception {
        Delivery delivery = new Delivery("123");
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);

        when(deliveryService.updateStatusAsync("123", DeliveryStatus.DIPROSES))
                .thenReturn(CompletableFuture.completedFuture(delivery));

        CompletableFuture<Delivery> futureDelivery = deliveryController.updateDeliveryStatus("123", DeliveryStatus.DIPROSES);
        Delivery updatedDelivery = futureDelivery.get();

        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, updatedDelivery.getStatus());
        verify(deliveryService, times(1)).updateStatusAsync("123", DeliveryStatus.DIPROSES);
    }

    @Test
    void testUpdateDeliveryTransportation() {
        Transportation transportation = new Transportation("Truck");
        Delivery delivery = new Delivery("123");
        delivery.setTransportation(transportation);

        when(deliveryService.updateTransportation("123", transportation)).thenReturn(delivery);

        Delivery updatedDelivery = deliveryController.updateDeliveryTransportation("123", transportation);

        assertEquals(transportation, updatedDelivery.getTransportation());
        verify(deliveryService, times(1)).updateTransportation("123", transportation);
    }

    @Test
    void testDeleteDelivery() {
        Delivery delivery = new Delivery("123");
        when(deliveryService.deleteDelivery("123")).thenReturn(delivery);

        deliveryController.deleteDelivery("123");

        verify(deliveryService, times(1)).deleteDelivery("123");
    }
}
