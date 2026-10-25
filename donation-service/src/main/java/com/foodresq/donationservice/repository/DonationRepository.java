package com.foodresq.donationservice.repository;
import com.foodresq.donationservice.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DonationRepository extends JpaRepository<Donation,String>{}
