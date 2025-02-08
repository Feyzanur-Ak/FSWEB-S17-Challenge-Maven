package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Course {
    private int id;
    private String name;
    private int credit;
    private Grade grade;
    private double totalGpa;

    public Course(Object o, Object o1, Object o2, Object o3) {
    }
}
