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
@RequestMapping("/courses")
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
        throw new ApiException("Course not found with name " + name, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        // Eğer credit değeri 1 ile 4 arasında değilse, 400 Bad Request fırlat
        if (course.getCredit() <= 0 || course.getCredit() > 4) {
            throw new ApiException("Credit değeri geçersiz: " + course.getCredit(), HttpStatus.BAD_REQUEST);
        }

        // Yeni kurs oluştur
        double totalGpa;
        if (course.getCredit() <= 2) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        } else if (course.getCredit() == 3) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        } else {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }

        course.setTotalGpa(totalGpa);
        courses.add(course);
        return course;
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