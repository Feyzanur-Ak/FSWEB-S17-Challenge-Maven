package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor
@Data
public class LowCourseGpa implements  CourseGpa{
    @Override
    public int getGpa() {
        return 3;
    }
}
