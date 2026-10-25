package com.foodresq.logisticsservice.controller;
import com.foodresq.logisticsservice.entity.Pickup;
import com.foodresq.logisticsservice.repository.PickupRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/pickups") @CrossOrigin
public class PickupController {
 private final PickupRepository repo;
 public PickupController(PickupRepository repo){this.repo=repo;}
 @GetMapping public List<Pickup> all(){return repo.findAll();}
 @PostMapping public Pickup create(@RequestBody Pickup p){if(p.getStatus()==null)p.setStatus("SCHEDULED");return repo.save(p);}
 @PutMapping("/{id}/confirm") public Pickup confirm(@PathVariable String id){Pickup p=repo.findById(id).orElseThrow();p.setStatus("CONFIRMED");return repo.save(p);}
 @PutMapping("/{id}/complete") public Pickup complete(@PathVariable String id){Pickup p=repo.findById(id).orElseThrow();p.setStatus("COMPLETED");return repo.save(p);}
}
