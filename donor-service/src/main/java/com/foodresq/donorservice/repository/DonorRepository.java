package com.foodresq.donorservice.repository;
import com.foodresq.donorservice.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DonorRepository extends JpaRepository<Donor,String>{}
