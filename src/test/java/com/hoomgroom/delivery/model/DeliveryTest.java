package com.hoomgroom.delivery.model;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeliveryTest {

    @Test
    void testGetDeliveryStatus() {
        Delivery delivery = new Delivery("ABC123");
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
    }

    @Test
    void testGetTransportation() {
        Transportation transportation = new Transportation("Truck");
        Delivery delivery = new Delivery("ABC123");
        delivery.setTransportation(transportation);
        assertEquals("Truck", delivery.getTransportation().getType());
    }

    @Test
    void testGetTransportationNull() {
        Delivery delivery = new Delivery("ABC123");
        assertNull(delivery.getTransportation());
    }

    @Test
    void testGetKodeResi() {
        Delivery delivery = new Delivery("ABC123");
        assertEquals("ABC123", delivery.getKodeResi());
    }
}
