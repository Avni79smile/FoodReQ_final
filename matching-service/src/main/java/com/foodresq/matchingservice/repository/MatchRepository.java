package com.foodresq.matchingservice.repository;
import com.foodresq.matchingservice.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MatchRepository extends JpaRepository<Match,String>{}
