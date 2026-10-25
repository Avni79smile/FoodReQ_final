package com.foodresq.notificationservice.controller;
import com.foodresq.notificationservice.entity.Notification;
import com.foodresq.notificationservice.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/notifications") @CrossOrigin
public class NotificationController {
 private final NotificationRepository repo;
 public NotificationController(NotificationRepository repo){this.repo=repo;}
 @GetMapping public List<Notification> all(){return repo.findAll();}
 @PostMapping public Notification create(@RequestBody Notification n){if(n.getStatus()==null)n.setStatus("UNREAD");return repo.save(n);}
 @PutMapping("/{id}/read") public Notification read(@PathVariable String id){Notification n=repo.findById(id).orElseThrow();n.setStatus("READ");return repo.save(n);}
}
