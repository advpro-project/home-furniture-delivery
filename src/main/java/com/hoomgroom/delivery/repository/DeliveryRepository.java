package com.hoomgroom.delivery.repository;

import com.hoomgroom.delivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, String> {
    Delivery findByKodeResi(String kodeResi);
    boolean deleteByKodeResi(String kodeResi);
}
