package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Data
@Component
public class MediumCourseGpa implements  CourseGpa{
    @Override
    public int getGpa() {
        return 5;
    }
}
