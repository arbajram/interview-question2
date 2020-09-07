package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArrayService {
     
    @Autowired
    ArrayRepository repository;

    public ArrayEntity getArrayById(Integer id) throws RecordNotFoundException
    {
        Optional<ArrayEntity> array = repository.findById(id);
         
        if(array.isPresent()) {
            return array.get();
        } else {
            throw new RecordNotFoundException("No array record exist for given id");
        }
    }
     
    public ArrayEntity createArrayEntity(String array) throws RecordNotFoundException {
        ArrayEntity newEntity = new ArrayEntity();

        newEntity.setArray(array);

        newEntity = repository.save(newEntity);

        return newEntity;

    }
     

}
