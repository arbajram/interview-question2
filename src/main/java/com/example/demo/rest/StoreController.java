package com.example.demo.rest;

import com.example.demo.service.INumbersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    private final INumbersService numbersService;

    public StoreController(INumbersService numbersService) {
        this.numbersService = numbersService;
    }

    @PostMapping("/store")
    public Integer store(@RequestParam(name = "numbers") Integer[] numbers) {
        System.out.println("Storing array ...");
        return numbersService.store(numbers);
    }


}
