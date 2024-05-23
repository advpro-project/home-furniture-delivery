package com.hoomgroom.delivery.model;

import com.hoomgroom.authentication.buyproduct.model.Furniture;
import com.hoomgroom.delivery.enums.DeliveryStatus;
import org.junit.jupiter.api.Test;
import com.hoomgroom.authentication.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void testDeliveryBuilder(){
        Transportation transportation = new Transportation("Truck");
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitureList = Arrays.asList(furniture1, furniture2);
        User user = new User();

        Delivery delivery = new DeliveryBuilder()
                .status(DeliveryStatus.MENUNGGU_VERIFIKASI)
                .transportation(transportation)
                .furnitureList(furnitureList)
                .user(user)
                .build();

        assertNotNull(delivery);
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
        assertEquals("Truck", delivery.getTransportation().getType());
        assertEquals(furnitureList, delivery.getFurnitureList());
        assertEquals(user, delivery.getUser());
        assertNull(delivery.getKodeResi(), "kodeResi should be null initially");

        delivery.setKoderesi();

        assertNotNull(delivery.getKodeResi(), "kodeResi should be set");
        assertTrue(delivery.getKodeResi().startsWith("HG-"), "kodeResi should start with 'HG-'");
    }
}
