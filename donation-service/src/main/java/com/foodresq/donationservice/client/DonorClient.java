package com.foodresq.donationservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(name="donor-service")
public interface DonorClient {
 @GetMapping("/donors/{id}") Object getDonor(@PathVariable("id") String id);
}
