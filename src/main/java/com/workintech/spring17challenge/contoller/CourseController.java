package com.workintech.spring17challenge.contoller;


import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.entity.HighCourseGpa;
import com.workintech.spring17challenge.entity.LowCourseGpa;
import com.workintech.spring17challenge.entity.MediumCourseGpa;
import com.workintech.spring17challenge.exceptions.ApiException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workintech/courses")
public class CourseController {

    private List<Course> courses;
    private final HighCourseGpa highCourseGpa;
    private final MediumCourseGpa mediumCourseGpa;
    private final  LowCourseGpa lowCourseGpa;

    @PostConstruct
    public void init() {
        courses = new ArrayList<>();
    }

    @Autowired
    public CourseController(HighCourseGpa highCourseGpa, MediumCourseGpa mediumCourseGpa, LowCourseGpa lowCourseGpa) {
        this.highCourseGpa = highCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.lowCourseGpa = lowCourseGpa;
    }


    @GetMapping
    public List<Course> getAllCourses() {
        return courses.stream().toList();
    }

    @GetMapping("/{name}")
    public Course getCourseByName(@PathVariable String name) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return course;
            }
        }

        throw new ApiException("Course not found with name" + name, HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        Course newCourse = new Course(course.getId(), course.getName(), course.getCredit(), course.getGrade(),course.getTotalGpa());

        if (newCourse.getCredit() > 0 && newCourse.getCredit() < 4) {
            throw new ApiException("Credit değeri geçersiz " + newCourse.getCredit(), HttpStatus.FORBIDDEN);
        }

        double totalGpa;
        if (newCourse.getCredit() <= 2) {
            totalGpa = newCourse.getGrade().getCoefficient() * newCourse.getCredit() * lowCourseGpa.getGpa();
        } else if (newCourse.getCredit() == 3) {
            totalGpa = newCourse.getGrade().getCoefficient() * newCourse.getCredit() * mediumCourseGpa.getGpa();
        } else {
            totalGpa = newCourse.getGrade().getCoefficient() * newCourse.getCredit() * highCourseGpa.getGpa();
        }

        newCourse.setTotalGpa(totalGpa);
        courses.add(newCourse);
        return newCourse;
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {

        for (Course existingCourse : courses) {
            if (existingCourse.getId() == id) {
                existingCourse.setId(course.getId());
                return existingCourse;
            }
        }
        throw new ApiException("Course not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public Course deleteCourse(@PathVariable int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                courses.remove(course);
                return course;
            }
        }
        throw new ApiException("Course not found with id: " + id, HttpStatus.NOT_FOUND);

    }
}