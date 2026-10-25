package com.foodresq.matchingservice.controller;
import com.foodresq.matchingservice.entity.Match;
import com.foodresq.matchingservice.repository.MatchRepository;
import com.foodresq.matchingservice.client.MLClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/matches") @CrossOrigin
public class MatchingController {
 private final MatchRepository repo; private final MLClient ml;
 @Value("${foodresq.matching.distance-weight:0.20}") double dw;
 @Value("${foodresq.matching.expiry-weight:0.20}") double ew;
 @Value("${foodresq.matching.quantity-weight:0.20}") double qw;
 @Value("${foodresq.matching.urgency-weight:0.20}") double uw;
 @Value("${foodresq.matching.pickup-weight:0.20}") double pw;
 public MatchingController(MatchRepository repo,MLClient ml){this.repo=repo;this.ml=ml;}
 @GetMapping public List<Match> all(){return repo.findAll();}
 @PostMapping("/score") public Match score(@RequestBody Map<String,Object> f){
   double distance=clamp(f.get("distanceScore"),50), expiry=clamp(f.get("expiryScore"),70),
          quantity=clamp(f.get("quantityScore"),80), urgency=clamp(f.get("urgencyScore"),80),
          pickup=clamp(f.get("pickupScore"),100);
   double rule=distance*dw+expiry*ew+quantity*qw+urgency*uw+pickup*pw;
   Map<String,Object> mlInput=new HashMap<>(f);
   Map<String,Object> prediction=ml.predict(mlInput);
   double probability=((Number)prediction.getOrDefault("acceptance_probability",0.5)).doubleValue();
   Match m=new Match();
   m.setDonationId(String.valueOf(f.getOrDefault("donationId","demo-donation")));
   m.setRecipientId(String.valueOf(f.getOrDefault("recipientId","demo-recipient")));
   m.setRuleScore(rule); m.setMlProbability(probability); m.setFinalScore(rule*0.70+probability*100*0.30);
   m.setStatus("RECOMMENDED");
   m.setExplanation(String.format("Rule score %.1f/100 + ML acceptance probability %.1f%%; final score %.1f/100",rule,probability*100,m.getFinalScore()));
   return repo.save(m);
 }
 private double clamp(Object x,double d){if(x==null)return d;try{return Math.max(0,Math.min(100,Double.parseDouble(x.toString())));}catch(Exception e){return d;}}
}
