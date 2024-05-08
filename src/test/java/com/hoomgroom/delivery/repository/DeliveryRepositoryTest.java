package com.hoomgroom.delivery.repository;

import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.model.Transportation;
import com.hoomgroom.delivery.service.DeliveryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliveryRepositoryTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAndFind() {
        Delivery delivery = new Delivery("ABC123");
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);
        Transportation transportation = new Transportation("Truck");
        delivery.setTransportation(transportation);

        when(deliveryRepository.save(delivery)).thenReturn(delivery);

        List<Delivery> allDeliveries = new ArrayList<>();
        allDeliveries.add(delivery);
        when(deliveryRepository.findAll()).thenReturn(allDeliveries);

        Delivery savedDelivery = deliveryService.createDelivery(delivery);
        assertEquals(delivery, savedDelivery);

        Iterable<Delivery> deliveries = deliveryService.findAllDeliveries();
        Iterator<Delivery> deliveryIterator = deliveries.iterator();
        assertTrue(deliveryIterator.hasNext());

        Delivery foundDelivery = deliveryIterator.next();
        assertEquals(delivery.getKodeResi(), foundDelivery.getKodeResi());
        assertEquals(delivery.getStatus(), foundDelivery.getStatus());
        assertEquals(delivery.getTransportation().getType(), foundDelivery.getTransportation().getType());
    }

    @Test
    void testFindAllIfEmpty() {
        when(deliveryRepository.findAll()).thenReturn(new ArrayList<>());
        Iterable<Delivery> deliveries = deliveryService.findAllDeliveries();
        assertFalse(deliveries.iterator().hasNext());
        verify(deliveryRepository, times(1)).findAll();
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

        
        List<Delivery> allDeliveries = new ArrayList<>();
        allDeliveries.add(delivery1);
        allDeliveries.add(delivery2);
        when(deliveryRepository.findAll()).thenReturn(allDeliveries);

        Iterable<Delivery> deliveries = deliveryService.findAllDeliveries();

        Iterator<Delivery> deliveryIterator = deliveries.iterator();
        assertTrue(deliveryIterator.hasNext());

        Delivery savedDelivery = deliveryIterator.next();
        assertEquals(delivery1.getKodeResi(), savedDelivery.getKodeResi());
        savedDelivery = deliveryIterator.next();
        assertEquals(delivery2.getKodeResi(), savedDelivery.getKodeResi());
        assertFalse(deliveryIterator.hasNext());

        verify(deliveryRepository, times(1)).findAll();
    }

    @Test
    void testFindByKodeResi() {
       
        Delivery delivery = new Delivery("ABC123");
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);
        Transportation transportation = new Transportation("Truck");
        delivery.setTransportation(transportation);

        
        when(deliveryRepository.findByKodeResi("ABC123")).thenReturn(delivery);

        
        Delivery foundDelivery = deliveryService.findByKodeResi("ABC123");

        
        assertNotNull(foundDelivery);
        assertEquals(delivery.getKodeResi(), foundDelivery.getKodeResi());
        assertEquals(delivery.getStatus(), foundDelivery.getStatus());
        assertEquals(delivery.getTransportation().getType(), foundDelivery.getTransportation().getType());

        
        verify(deliveryRepository, times(1)).findByKodeResi("ABC123");
    }

    @Test
    void testFindByKodeResiIfDoesNotExist() {
        
        when(deliveryRepository.findByKodeResi("nonexistentKodeResi")).thenReturn(null);

        
        Delivery foundDelivery = deliveryService.findByKodeResi("nonexistentKodeResi");

        
        assertNull(foundDelivery);

        verify(deliveryRepository, times(1)).findByKodeResi("nonexistentKodeResi");
    }
}
