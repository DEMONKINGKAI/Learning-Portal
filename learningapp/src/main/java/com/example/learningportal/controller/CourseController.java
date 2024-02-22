package com.example.learningportal.controller;

import com.example.learningportal.dto.CourseDTO;
import com.example.learningportal.entity.Course;
import com.example.learningportal.entity.User;
import com.example.learningportal.service.CourseService;
import com.example.learningportal.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) {


        User user = UserService.getCurrentUser();


        if (courseDTO == null) {
            log.info("Course Details Required");
            return ResponseEntity.badRequest().body("Course details are required");
        }


        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        System.out.println(course);

        try {
            Course createdCourse = courseService.createCourse(user, course);
            log.info("reached");
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create course: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {

        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        courseService.updateCourse(id, course);
        log.info("Course Updated");
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        log.info("Course Deleted");
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }
}
