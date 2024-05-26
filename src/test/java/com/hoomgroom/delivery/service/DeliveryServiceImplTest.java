package com.hoomgroom.delivery.service;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceImplTest {

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Test
    void testCreateDelivery() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.save(delivery)).thenReturn(delivery);

        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        assertNotNull(createdDelivery);
        assertEquals("ABC123", createdDelivery.getKodeResi());
    }

    @Test
    void testFindAllDeliveries() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.findAll()).thenReturn(Collections.singletonList(delivery));

        List<Delivery> deliveries = deliveryService.findAllDeliveries();
        assertNotNull(deliveries);
        assertEquals(1, deliveries.size());
        assertEquals("ABC123", deliveries.get(0).getKodeResi());
    }

    @Test
    void testFindByKodeResi() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.findByKodeResi("ABC123")).thenReturn(delivery);

        Delivery foundDelivery = deliveryService.findByKodeResi("ABC123");

        assertNotNull(foundDelivery);
        assertEquals("ABC123", foundDelivery.getKodeResi());
    }

    @Test
    void testFindByKodeResiNonExisting() {
        when(deliveryRepository.findByKodeResi("NONEXISTING")).thenReturn(null);

        Delivery foundDelivery = deliveryService.findByKodeResi("NONEXISTING");

        assertNull(foundDelivery);
    }

    @Test
    void testUpdateStatusAsync() throws Exception {
        String kodeResi = "ABC123";
        Delivery delivery = new Delivery(kodeResi);
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);

        // Mock the asynchronous behavior
        when(deliveryRepository.findByKodeResi(kodeResi)).thenReturn(delivery);
        when(deliveryRepository.save(delivery)).thenReturn(delivery);

        // Call the asynchronous method
        CompletableFuture<Delivery> futureDelivery = deliveryService.updateStatusAsync(kodeResi, DeliveryStatus.DIPROSES);

        // Wait for the asynchronous computation to complete
        Delivery updatedDelivery = futureDelivery.get();

        // Assert the result
        assertEquals(DeliveryStatus.DIPROSES, updatedDelivery.getStatus());
    }

    @Test
    void testUpdateTransportation() {
        String kodeResi = "ABC123";
        Delivery delivery = new Delivery(kodeResi);
        delivery.setStatus(DeliveryStatus.DIPROSES);
        Transportation transportation = new Transportation("Truck");

        when(deliveryRepository.findByKodeResi(kodeResi)).thenReturn(delivery);
        when(deliveryRepository.save(delivery)).thenReturn(delivery);

        Delivery updatedDelivery = deliveryService.updateTransportation(kodeResi, transportation);

        assertNotNull(updatedDelivery);
        assertEquals("Truck", updatedDelivery.getTransportation().getType());
    }

    @Test
    void testUpdateTransportationInvalidStatus() {
        String kodeResi = "ABC123";
        Delivery delivery = new Delivery(kodeResi);
        delivery.setStatus(DeliveryStatus.DIKIRIM);
        Transportation transportation = new Transportation("Truck");

        when(deliveryRepository.findByKodeResi(kodeResi)).thenReturn(delivery);

        Delivery updatedDelivery = deliveryService.updateTransportation(kodeResi, transportation);

        assertNull(updatedDelivery);
    }

    @Test
    void testDeleteDelivery() {
        when(deliveryRepository.deleteByKodeResi("ABC123")).thenReturn(true);

        Delivery deletedDelivery = deliveryService.deleteDelivery("ABC123");

        assertNotNull(deletedDelivery);
        assertEquals("ABC123", deletedDelivery.getKodeResi());
    }

    @Test
    void testDeleteDeliveryNonExisting() {
        when(deliveryRepository.deleteByKodeResi("NONEXISTING")).thenReturn(false);

        Delivery deletedDelivery = deliveryService.deleteDelivery("NONEXISTING");

        assertNull(deletedDelivery);
    }
}
