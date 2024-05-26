package com.hoomgroom.delivery.model;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryBuilderTest {

    @Test
    void testDefaultBuild() {
        Delivery delivery = new DeliveryBuilder().build();
        
        assertNotNull(delivery);
        assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
        assertNull(delivery.getTransportation());
        assertNull(delivery.getFurnitureList());
        assertNull(delivery.getUserDelivery());
    }

    @Test
    void testBuildWithStatus() {
        Delivery delivery = new DeliveryBuilder()
                .status(DeliveryStatus.DIKIRIM)
                .build();
        
        assertNotNull(delivery);
        assertEquals(DeliveryStatus.DIKIRIM, delivery.getStatus());
    }

    @Test
    void testBuildWithTransportation() {
        Transportation transportation = new Transportation("Truck");
        
        Delivery delivery = new DeliveryBuilder()
                .transportation(transportation)
                .build();
        
        assertNotNull(delivery);
        assertEquals(transportation, delivery.getTransportation());
        assertEquals("Truck", delivery.getTransportation().getType());
    }

    @Test
    void testBuildWithFurnitureList() {
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitureList = Arrays.asList(furniture1, furniture2);
        
        Delivery delivery = new DeliveryBuilder()
                .furnitureList(furnitureList)
                .build();
        
        assertNotNull(delivery);
        assertEquals(furnitureList, delivery.getFurnitureList());
    }

    @Test
    void testBuildWithUser() {
        User user = new User();
        
        Delivery delivery = new DeliveryBuilder()
                .user(user)
                .build();
        
        assertNotNull(delivery);
        assertEquals(user, delivery.getUserDelivery());
    }

    @Test
    void testFullBuild() {
        DeliveryStatus status = DeliveryStatus.DIKIRIM;
        Transportation transportation = new Transportation("Truck");
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitureList = Arrays.asList(furniture1, furniture2);
        User user = new User();

        Delivery delivery = new DeliveryBuilder()
                .status(status)
                .transportation(transportation)
                .furnitureList(furnitureList)
                .user(user)
                .build();
        
        assertNotNull(delivery);
        assertEquals(status, delivery.getStatus());
        assertEquals(transportation, delivery.getTransportation());
        assertEquals(furnitureList, delivery.getFurnitureList());
        assertEquals(user, delivery.getUserDelivery());
    }
}
