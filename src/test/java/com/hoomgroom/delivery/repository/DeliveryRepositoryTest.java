package com.hoomgroom.delivery.repository;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    void testCreateAndFind() {
        Transportation transportation = new Transportation("Truck");
        Delivery delivery = new Delivery(DeliveryStatus.MENUNGGU_VERIFIKASI, transportation, "ABC123");
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
        Transportation transportation1 = new Transportation("Truck");
        Delivery delivery1 = new Delivery(DeliveryStatus.MENUNGGU_VERIFIKASI, transportation1, "ABC123");
        deliveryRepository.save(delivery1);

        Transportation transportation2 = new Transportation("Bike");
        Delivery delivery2 = new Delivery(DeliveryStatus.DIPROSES, transportation2, "DEF456");
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
        Transportation transportation = new Transportation("Truck");
        Delivery delivery = new Delivery(DeliveryStatus.MENUNGGU_VERIFIKASI, transportation, "ABC123");
        deliveryRepository.save(delivery);

        Delivery foundDelivery = deliveryRepository.findByKodeResi(delivery.getKodeResi()).orElse(null);
        assertNotNull(foundDelivery);
        assertEquals(delivery.getKodeResi(), foundDelivery.getKodeResi());
        assertEquals(delivery.getStatus(), foundDelivery.getStatus());
        assertEquals(delivery.getTransportation().getType(), foundDelivery.getTransportation().getType());
    }

    @Test
    void testFindByKodeResiIfDoesNotExist() {
        Delivery foundDelivery = deliveryRepository.findByKodeResi("nonexistentKodeResi").orElse(null);
        assertNull(foundDelivery);
    }
}
