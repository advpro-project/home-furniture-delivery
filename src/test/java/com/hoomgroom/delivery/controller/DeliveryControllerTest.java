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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
