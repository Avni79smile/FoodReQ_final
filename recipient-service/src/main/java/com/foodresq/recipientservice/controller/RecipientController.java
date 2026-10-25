package com.foodresq.recipientservice.controller;
import com.foodresq.recipientservice.entity.Recipient;
import com.foodresq.recipientservice.repository.RecipientRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/recipients") @CrossOrigin
public class RecipientController {
  private final RecipientRepository repo;
  public RecipientController(RecipientRepository repo){this.repo=repo;}
  @GetMapping public List<Recipient> all(){return repo.findAll();}
  @GetMapping("/{id}") public Recipient one(@PathVariable String id){return repo.findById(id).orElseThrow();}
  @PostMapping public Recipient create(@RequestBody Recipient x){return repo.save(x);}
  @PutMapping("/{id}") public Recipient update(@PathVariable String id,@RequestBody Recipient x){x.setId(id);return repo.save(x);}
  @DeleteMapping("/{id}") public void delete(@PathVariable String id){repo.deleteById(id);}
}
