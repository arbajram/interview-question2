package com.example.demo.rest;

import com.example.demo.service.INumbersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermutationController {

    private final INumbersService numbersService;

    public PermutationController(INumbersService numbersService) {
        this.numbersService = numbersService;
    }

    @GetMapping("/permutation")
    public Integer[] permutation(@RequestParam(name = "id") Integer id) {
        return numbersService.permutation(id);
    }


}
