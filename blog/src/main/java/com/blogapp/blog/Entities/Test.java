package com.blogapp.blog.Entities;

import org.springframework.data.mapping.AccessOptions.GetOptions.GetNulls;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "tests")
@NoArgsConstructor
@Setter
@Getter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testId")
    private int testId;

    private String task;

    
}
