package com.example.demo.rest;

import com.example.demo.service.NumbersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    private final NumbersService numbersService;

    public StoreController(NumbersService numbersService) {
        this.numbersService = numbersService;
    }

    @PostMapping("/store")
    public Integer store(@RequestParam(name = "numbers") Integer[] numbers) {
        return numbersService.store(numbers);
    }


}
