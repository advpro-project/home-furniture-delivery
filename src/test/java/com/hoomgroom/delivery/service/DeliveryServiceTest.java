import com.hoomgroom.delivery.enums.DeliveryStatus;
import com.hoomgroom.delivery.model.Delivery;
import com.hoomgroom.delivery.repository.DeliveryRepository;
import com.hoomgroom.delivery.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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
        doReturn(true).when(deliveryRepository).existsByKodeResi("ABC123");

        assertThrows(IllegalArgumentException.class, () -> deliveryService.createDelivery(existingDelivery));

        verify(deliveryRepository, never()).save(existingDelivery);
    }

    @Test
    void testUpdateStatus() {
        Delivery delivery = new Delivery("ABC123");
        delivery.setStatus(DeliveryStatus.MENUNGGU_VERIFIKASI);
        when(deliveryRepository.findByKodeResi("ABC123")).thenReturn(delivery);

        assertTrue(deliveryService.updateStatus("ABC123", DeliveryStatus.DIPROSES));

        assertEquals(DeliveryStatus.DIPROSES, delivery.getStatus());
        verify(deliveryRepository, times(1)).edit(delivery);
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Delivery delivery = new Delivery("ABC123");
        when(deliveryRepository.findByKodeResi("ABC123")).thenReturn(delivery);

        assertThrows(IllegalArgumentException.class, () -> deliveryService.updateStatus("ABC123", DeliveryStatus.valueOf("INVALID")));

        verify(deliveryRepository, never()).edit(delivery);
    }

    @Test
    void testUpdateStatusNonExistingDelivery() {
        when(deliveryRepository.findByKodeResi("NONEXISTING")).thenReturn(null);

        assertFalse(deliveryService.updateStatus("NONEXISTING", DeliveryStatus.DIPROSES));

        verify(deliveryRepository, never()).edit(any());
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
        when(deliveryRepository.delete("ABC123")).thenReturn(delivery);

        Delivery deletedDelivery = deliveryService.deleteDeliveryByKodeResi("ABC123");

        assertNotNull(deletedDelivery);
        assertEquals("ABC123", deletedDelivery.getKodeResi());
    }
}
