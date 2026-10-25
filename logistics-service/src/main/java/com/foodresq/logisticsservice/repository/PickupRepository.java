package com.foodresq.logisticsservice.repository;
import com.foodresq.logisticsservice.entity.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PickupRepository extends JpaRepository<Pickup,String>{}
