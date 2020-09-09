package com.example.demo.service;

import com.example.demo.utils.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NumbersMemoryService implements NumbersService {
    private final String LAST_ID = "lastId";

    @Autowired
    private CacheHelper cache;

    static final AtomicInteger counter = new AtomicInteger(0);

    public NumbersMemoryService(){

    };

    public int store(Integer[] integers) {

        //Get thread safe next id ...
        Integer nextId = counter.incrementAndGet();
        cache.getStoredArrayCacheFromCacheManager().put(nextId, integers);

        return nextId;
    }

    public Integer[] permutation(Integer id)
    {
        //Checking existence of the array
        if (!cache.getStoredArrayCacheFromCacheManager().containsKey(id))
        {
            throw new NoSuchElementException(String.format("Array with id = %s does not exist", id));
        }

        //Get array and shuuffle
        Integer[] integers = cache.getStoredArrayCacheFromCacheManager().get(id);
        List<Integer> intList = Arrays.asList(integers);
        Collections.shuffle(intList);
        intList.toArray(integers);

        return integers;
    }
}
