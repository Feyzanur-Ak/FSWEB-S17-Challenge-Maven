package com.workintech.spring17challenge.contoller;


import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.entity.HighCourseGpa;
import com.workintech.spring17challenge.entity.LowCourseGpa;
import com.workintech.spring17challenge.entity.MediumCourseGpa;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

   private  List<Course> courses;
   private HighCourseGpa highCourseGpa;
   private MediumCourseGpa mediumCourseGpa;
   private LowCourseGpa lowCourseGpa;

    @PostConstruct
    public void init(){
        courses=new ArrayList<>();
    }

     @Autowired
     public CourseController(List<Course> courses, HighCourseGpa highCourseGpa, MediumCourseGpa mediumCourseGpa, LowCourseGpa lowCourseGpa) {
        this.courses = courses;
        this.highCourseGpa = highCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.lowCourseGpa = lowCourseGpa;
    }
}
