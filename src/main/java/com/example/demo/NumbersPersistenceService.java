package com.example.demo;

import com.example.demo.client.ArrayPersistanceHttpClient;
import com.example.demo.persistence.ArrayEntity;
import com.example.demo.utils.CacheHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NumbersPersistenceService {
    private final ArrayPersistanceHttpClient arrayPersistanceHttpClient;

    public NumbersPersistenceService(CacheHelper cache, ArrayPersistanceHttpClient arrayPersistanceHttpClient) {
        this.arrayPersistanceHttpClient = arrayPersistanceHttpClient;
    }

    public int store(Integer[] integers) {
       return arrayPersistanceHttpClient.createArray(Arrays.toString(integers));
    }

    public Integer[] permutation(Integer id)
    {

        System.out.println("Getting array by id = " + id);
        ResponseEntity<ArrayEntity> responseEntity = arrayPersistanceHttpClient.getArrayById(id);

        String array = responseEntity.getBody().getArray();

        System.out.println("Array = " + array);

        String[] items = array.substring(1, array.length() - 1).split(",");

        List<String> stringList = Arrays.asList(items);

        Collections.shuffle(stringList);

        List<Integer> intList = stringList.stream().map(a -> Integer.parseInt(a.trim())).collect(Collectors.toList());

        Integer[] integers = new Integer[intList.size()];

        intList.toArray(integers);

        return integers;
    }
}
