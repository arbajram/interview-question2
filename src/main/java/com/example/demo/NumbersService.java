package com.example.demo;

import com.example.demo.utils.CacheHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NumbersService {
    private final String LAST_ID = "lastId";

    private final CacheHelper cache;

    public NumbersService(CacheHelper cache) {
        this.cache = cache;
    }

    public int store(Integer[] integers) {

        Integer lastId = 0;
        if (cache.getGenericCache().containsKey(LAST_ID))
        {
            lastId = cache.getGenericCache().get(LAST_ID);
        }

        Integer nextId = lastId + 1;
        cache.getStoredArrayCacheFromCacheManager().put(nextId, integers);

        cache.getGenericCache().put(LAST_ID, nextId);

        return nextId;
    }

    public Integer[] permutation(Integer id)
    {
        if (!cache.getStoredArrayCacheFromCacheManager().containsKey(id))
        {
            throw new NoSuchElementException(String.format("Array with id = %s does not exist", id));
        }

        Integer[] integers = cache.getStoredArrayCacheFromCacheManager().get(id);

        List<Integer> intList = Arrays.asList(integers);

        Collections.shuffle(intList);

        intList.toArray(integers);

        return integers;
    }
}
