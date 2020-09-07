package com.example.demo.client;

import com.example.demo.persistence.ArrayEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${array-persistence-client.service-name}", url = "${array-persistence-client.url}")
public interface ArrayPersistanceHttpClient {

    @RequestMapping(consumes = "application/json", path = "/{id}")
    ResponseEntity<ArrayEntity> getArrayById(@PathVariable("id") Integer id);

    @PostMapping(consumes = "application/json")
    Integer createArray(@RequestParam(name = "array", required = true) String array);
}
