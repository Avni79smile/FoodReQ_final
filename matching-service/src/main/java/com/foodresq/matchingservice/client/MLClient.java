package com.foodresq.matchingservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@FeignClient(name="ml-service", url="${ML_SERVICE_URL:http://localhost:8090}")
public interface MLClient {
 @PostMapping("/predict") Map<String,Object> predict(@RequestBody Map<String,Object> features);
}
