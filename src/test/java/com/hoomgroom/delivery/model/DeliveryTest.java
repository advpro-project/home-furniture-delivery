package com.hoomgroom.delivery.model;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    @Test
    void testDefaultConstructor() {
        Delivery delivery = new Delivery();
        assertNotNull(delivery);
        assertNull(delivery.getKodeResi());
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
        assertNull(delivery.getTransportation());
        assertNull(delivery.getFurnitureList());
        assertNull(delivery.getUserDelivery());
    }

    @Test
    void testParameterizedConstructor() {
        Delivery delivery = new Delivery("ABC123");
        assertNotNull(delivery);
        assertEquals("ABC123", delivery.getKodeResi());
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
    }

    @Test
    void testFullParameterizedConstructor() {
        Transportation transportation = new Transportation("Truck");
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitureList = Arrays.asList(furniture1, furniture2);
        User user = new User();

        Delivery delivery = new Delivery(DeliveryStatus.DIKIRIM, transportation, furnitureList, user);

        assertNotNull(delivery);
        assertEquals(DeliveryStatus.DIKIRIM, delivery.getStatus());
        assertEquals(transportation, delivery.getTransportation());
        assertEquals(furnitureList, delivery.getFurnitureList());
        assertEquals(user, delivery.getUserDelivery());
    }

    @Test
    void testSetKoderesi() {
        Delivery delivery = new Delivery();
        delivery.setKoderesi();

        assertNotNull(delivery.getKodeResi(), "kodeResi should be set");
        assertTrue(delivery.getKodeResi().startsWith("HG-"), "kodeResi should start with 'HG-'");
    }

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
    void testSetAndGetFurnitureList() {
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitureList = Arrays.asList(furniture1, furniture2);

        Delivery delivery = new Delivery("ABC123");
        delivery.setFurnitureList(furnitureList);

        assertEquals(furnitureList, delivery.getFurnitureList());
    }

    @Test
    void testSetAndGetUserDelivery() {
        User user = new User();
        Delivery delivery = new Delivery("ABC123");
        delivery.setUserDelivery(user);

        assertEquals(user, delivery.getUserDelivery());
    }

    @Test
    void testDeliveryBuilder() {
        Transportation transportation = new Transportation("Truck");
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitureList = Arrays.asList(furniture1, furniture2);
        User user = new User();

        Delivery delivery = new Delivery(DeliveryStatus.MENUNGGU_VERIFIKASI, transportation, furnitureList, user);

        assertNotNull(delivery);
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
        assertEquals("Truck", delivery.getTransportation().getType());
        assertEquals(furnitureList, delivery.getFurnitureList());
        assertEquals(user, delivery.getUserDelivery());

        delivery.setKoderesi();

        assertNotNull(delivery.getKodeResi(), "kodeResi should be set");
        assertTrue(delivery.getKodeResi().startsWith("HG-"), "kodeResi should start with 'HG-'");
    }
}
