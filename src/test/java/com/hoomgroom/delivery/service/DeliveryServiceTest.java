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

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

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
    void testUpdate() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.save(delivery)).thenReturn(delivery);
        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        when(deliveryRepository.edit(createdDelivery)).thenReturn(true);
        // Ini dibutuhkan karena pada test tidak benar benar menambahkan ke repository (mock behavior)
        when(deliveryRepository.findByKodeResi(createdDelivery.getKodeResi())).thenReturn(createdDelivery);
        Delivery updatedProduct = deliveryService.updateStatus(createdDelivery.getKodeResi(), DeliveryStatus.TIBA);
        assertEquals(DeliveryStatus.TIBA, updatedProduct.getStatus());
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
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.deleteByKodeResi("ABC123")).thenReturn(true);

        Delivery deletedDelivery = deliveryService.deleteDelivery("ABC123");

        assertNotNull(deletedDelivery);
        assertEquals("ABC123", deletedDelivery.getKodeResi());
    }

}
