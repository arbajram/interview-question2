package com.example.demo.persistence;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="TBL_ARRAYS")
public class ArrayEntity {
 
    @Id
    @GeneratedValue
    private Integer id;
     
    @Column(name="array_as_string")
    private String array;

}
