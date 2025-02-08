package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;





@AllArgsConstructor
@Data
public class HighCourseGpa implements CourseGpa{
    @Override
    public int getGpa() {
        return 10;
    }
}
