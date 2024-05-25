package com.hoomgroom.delivery.service;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.CompletableFuture;

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
    void testDeleteDelivery() {
        when(deliveryRepository.deleteByKodeResi("ABC123")).thenReturn(true);

        Delivery deletedDelivery = deliveryService.deleteDelivery("ABC123");

        assertNotNull(deletedDelivery);
        assertEquals("ABC123", deletedDelivery.getKodeResi());
    }

}
