package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermutationController {

    private final NumbersPersistenceService numbersService;

    public PermutationController(NumbersPersistenceService numbersService) {
        this.numbersService = numbersService;
    }

    @GetMapping("/permutation")
    public Integer[] permutation(@RequestParam(name = "id", required = true) Integer id) {
        return numbersService.permutation(id);
    }


}
