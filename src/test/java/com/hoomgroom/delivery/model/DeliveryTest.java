import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {
    @Test
    public void testModelDelivery() {
        Transportation transportation;
        Delivery delivery;

        @BeforeEach
        void setUp(){
            this.transportation = new Transportation();
            this.transportation.setType("Truck");
            this.delivery = new ModelDelivery(DeliveryStatus.MENUNGGU_VERIFIKASI, transportation, "ABC123");
        }

        @Test
        void testGetDeliveryStatus(){
            assertEquals(DeliveryStatus.MENUNGGU_VERIFIKASI, delivery.getStatus());
        }

        @Test
        void testGetTransportation(){
            assertEquals("Truck", delivery.getTransportation().getType());
        }

        @Test
        void testGetKoderesi(){
            assertEquals("ABC123", delivery.getKodeResi());
        }
    }
}
