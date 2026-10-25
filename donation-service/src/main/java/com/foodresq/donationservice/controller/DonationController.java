package com.foodresq.donationservice.controller;
import com.foodresq.donationservice.entity.Donation;
import com.foodresq.donationservice.repository.DonationRepository;
import com.foodresq.donationservice.client.DonorClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/donations") @CrossOrigin
public class DonationController {
 private final DonationRepository repo; private final DonorClient donorClient;
 public DonationController(DonationRepository repo,DonorClient donorClient){this.repo=repo;this.donorClient=donorClient;}
 @GetMapping public List<Donation> all(){return repo.findAll();}
 @GetMapping("/{id}") public Donation one(@PathVariable String id){return repo.findById(id).orElseThrow();}
 @PostMapping public Donation create(@RequestBody Donation x){if(x.getStatus()==null)x.setStatus("AVAILABLE");return repo.save(x);}
 @PutMapping("/{id}") public Donation update(@PathVariable String id,@RequestBody Donation x){x.setId(id);return repo.save(x);}
 @DeleteMapping("/{id}") public void delete(@PathVariable String id){repo.deleteById(id);}
 @GetMapping("/donor/{id}") public Object donor(@PathVariable String id){return donorClient.getDonor(id);}
}
