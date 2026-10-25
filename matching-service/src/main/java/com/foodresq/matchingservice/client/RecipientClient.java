package com.foodresq.matchingservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(name="recipient-service")
public interface RecipientClient {
 @GetMapping("/recipients/{id}") Object getRecipient(@PathVariable("id") String id);
 @GetMapping("/recipients") Object getRecipients();
}
