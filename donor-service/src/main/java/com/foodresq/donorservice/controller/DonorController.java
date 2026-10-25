package com.foodresq.donorservice.controller;
import com.foodresq.donorservice.entity.Donor;
import com.foodresq.donorservice.repository.DonorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/donors") @CrossOrigin
public class DonorController {
  private final DonorRepository repo;
  public DonorController(DonorRepository repo){this.repo=repo;}
  @GetMapping public List<Donor> all(){return repo.findAll();}
  @GetMapping("/{id}") public Donor one(@PathVariable String id){return repo.findById(id).orElseThrow();}
  @PostMapping public Donor create(@RequestBody Donor x){return repo.save(x);}
  @PutMapping("/{id}") public Donor update(@PathVariable String id,@RequestBody Donor x){x.setId(id);return repo.save(x);}
  @DeleteMapping("/{id}") public void delete(@PathVariable String id){repo.deleteById(id);}
}
