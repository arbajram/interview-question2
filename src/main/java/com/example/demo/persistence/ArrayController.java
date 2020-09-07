package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/array-persistence-service")
public class ArrayController {
    @Autowired
    ArrayService service;

    @GetMapping("/{id}")
    public ResponseEntity<ArrayEntity> getArrayById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        ArrayEntity entity = service.getArrayById(id);

        return new ResponseEntity<ArrayEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public Integer createArray(@RequestParam(name = "array", required = true) String array)
            throws RecordNotFoundException {
        ArrayEntity newEntity = service.createArrayEntity(array);
        return newEntity.getId();
    }


}
