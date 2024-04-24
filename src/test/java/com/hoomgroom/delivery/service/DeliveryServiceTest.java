package com.hoomgroom.delivery.service;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.repository.DeliveryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

    @InjectMocks
    private DeliveryServiceImpl productService;

    @Mock
    private DeliveryRepository productRepository;

    @Test
    void testCreateDelivery() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.save(delivery)).thenReturn(delivery);

        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        assertNotNull(createdDelivery);
        assertEquals("ABC123", createdDelivery.getKodeResi());

        verify(deliveryRepository, times(1)).save(delivery);
    }

    @Test
    void testCreateDeliveryAlreadyExisting() {
        Delivery existingDelivery = new Delivery("ABC123");
        when(deliveryRepository.findByKodeResi("ABC123")).thenReturn(existingDelivery);

        assertThrows(IllegalArgumentException.class, () -> deliveryService.createDelivery(existingDelivery));

        verify(deliveryRepository, never()).save(existingDelivery);
    }

    @Test
    void testUpdate() {
        Delivery delivery = new Delivery("ABC123");

        delivery.setStatus(DeliveryStatus.DIKIRIM);

        when(deliveryRepository.edit(delivery)).thenReturn(true);
        Delivery updatedProduct = deliveryService.updateStatus(delivery.getKodeResi(), DeliveryStatus.TIBA);

        assertEquals("TIBA", updatedProduct.getStatus());
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.findByKodeResi("ABC123")).thenReturn(delivery);

        assertThrows(IllegalArgumentException.class, () -> deliveryService.updateStatus("ABC123", DeliveryStatus.valueOf("INVALID")));

        verify(deliveryRepository, never()).edit(delivery);
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
        when(deliveryRepository.delete("ABC123")).thenReturn(true);

        Delivery deletedDelivery = deliveryService.deleteDelivery("ABC123");

        assertNotNull(deletedDelivery);
        assertEquals("ABC123", deletedDelivery.getKodeResi());
    }

}
