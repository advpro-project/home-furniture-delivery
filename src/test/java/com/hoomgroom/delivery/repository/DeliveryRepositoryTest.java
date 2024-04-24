package com.hoomgroom.delivery.repository;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryRepositoryTest {

    private final DeliveryRepository deliveryRepository = new DeliveryRepository();

    @Test
    void testCreateAndFind() {
        Delivery delivery = new Delivery("ABC123");
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);
        Transportation transportation = new Transportation("Truck");
        delivery.setTransportation(transportation);

        deliveryRepository.save(delivery);

        Iterator<Delivery> deliveryIterator = deliveryRepository.findAll();
        assertTrue(deliveryIterator.hasNext());

        Delivery savedDelivery = deliveryIterator.next();
        assertEquals(delivery.getKodeResi(), savedDelivery.getKodeResi());
        assertEquals(delivery.getStatus(), savedDelivery.getStatus());
        assertEquals(delivery.getTransportation().getType(), savedDelivery.getTransportation().getType());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Delivery> deliveryIterator = deliveryRepository.findAll();
        assertFalse(deliveryIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneDelivery() {
        Delivery delivery1 = new Delivery("ABC123");
        delivery1.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);
        Transportation transportation1 = new Transportation("Truck");
        delivery1.setTransportation(transportation1);

        Delivery delivery2 = new Delivery("DEF456");
        delivery2.setStatus(DeliveryStatus.DIPROSES);
        Transportation transportation2 = new Transportation("Motor");
        delivery2.setTransportation(transportation2);

        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);

        Iterator<Delivery> deliveryIterator = deliveryRepository.findAll();
        assertTrue(deliveryIterator.hasNext());

        Delivery savedDelivery = deliveryIterator.next();
        assertEquals(delivery1.getKodeResi(), savedDelivery.getKodeResi());
        savedDelivery = deliveryIterator.next();
        assertEquals(delivery2.getKodeResi(), savedDelivery.getKodeResi());
        assertFalse(deliveryIterator.hasNext());
    }

    @Test
    void testFindByKodeResi() {
        Delivery delivery = new Delivery("ABC123");
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);
        Transportation transportation = new Transportation("Truck");
        delivery.setTransportation(transportation);

        deliveryRepository.save(delivery);

        Delivery foundDelivery = deliveryRepository.findByKodeResi(delivery.getKodeResi());
        assertNotNull(foundDelivery);
        assertEquals(delivery.getKodeResi(), foundDelivery.getKodeResi());
        assertEquals(delivery.getStatus(), foundDelivery.getStatus());
        assertEquals(delivery.getTransportation().getType(), foundDelivery.getTransportation().getType());
    }

    @Test
    void testFindByKodeResiIfDoesNotExist() {
        Delivery foundDelivery = deliveryRepository.findByKodeResi("nonexistentKodeResi");
        assertNull(foundDelivery);
    }

    @Test
    void testEditDelivery() {
        Delivery originalDelivery = new Delivery("ABC123");
        originalDelivery.setStatus(DeliveryStatus.DIPROSES);
        deliveryRepository.save(originalDelivery);

        Delivery updatedDelivery = new Delivery("ABC123");
        updatedDelivery.setStatus(DeliveryStatus.DIPROSES);
        Transportation updatedTransportation = new Transportation("Motor");
        updatedDelivery.setTransportation(updatedTransportation);

        assertTrue(deliveryRepository.edit(updatedDelivery));

        Delivery retrievedDelivery = deliveryRepository.findByKodeResi(updatedDelivery.getKodeResi());

        assertNotNull(retrievedDelivery);
        assertEquals(updatedDelivery.getStatus(), retrievedDelivery.getStatus());
        assertEquals(updatedDelivery.getTransportation().getType(), retrievedDelivery.getTransportation().getType());
    }
}
