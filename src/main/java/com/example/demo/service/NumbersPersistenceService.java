package com.example.demo.service;

import com.example.demo.client.ArrayPersistanceHttpClient;
import com.example.demo.persistence.ArrayEntity;
import com.example.demo.service.INumbersService;
import com.example.demo.utils.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NumbersPersistenceService implements INumbersService {

    @Autowired
    private ArrayPersistanceHttpClient arrayPersistanceHttpClient;

    public int store(Integer[] integers) {
       return arrayPersistanceHttpClient.createArray(Arrays.toString(integers));
    }

    public Integer[] permutation(Integer id)
    {
        //Getting raw array (as string) from db
        System.out.println("Getting array by id = " + id);
        ResponseEntity<ArrayEntity> responseEntity = arrayPersistanceHttpClient.getArrayById(id);
        String array = responseEntity.getBody().getArray();

        System.out.println("Array = " + array);

        //Converting string to shuffled integer array
        String[] items = array.substring(1, array.length() - 1).split(",");
        List<String> stringList = Arrays.asList(items);
        Collections.shuffle(stringList);
        List<Integer> intList = stringList.stream().map(a -> Integer.parseInt(a.trim())).collect(Collectors.toList());
        Integer[] integers = new Integer[intList.size()];
        intList.toArray(integers);

        return integers;
    }
}
