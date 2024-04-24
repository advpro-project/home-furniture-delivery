package com.hoomgroom.delivery.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hoomgroom.delivery.enums.DeliveryStatus;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {
    private Transportation transportation;
    private Delivery delivery;
    private DeliveryStatus deliveryStatus;

    @BeforeEach
    void setUp() {
        this.transportation = new Transportation("Truck");
        this.delivery = new Delivery(DeliveryStatus.MENUNGGU_VERIFIKASI, transportation, "ABC123");
    }

    @Test
    void testGetDeliveryStatus() {
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
    }

    @Test
    void testGetTransportation() {
        assertEquals("Truck", delivery.getTransportation().getType());
    }

    @Test
    void testGetKodeResi() {
        assertEquals("ABC123", delivery.getKodeResi());
    }
}
