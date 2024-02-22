package com.example.learningportal.service;

import com.example.learningportal.entity.Course;
import com.example.learningportal.entity.User;
import com.example.learningportal.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private static final String COURSE_NOT_FOUND_MESSAGE = "Course not found with id: ";

    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public Course createCourse(User user, Course course) {
        course.setAuthor(user);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElseThrow(() -> new IllegalArgumentException(COURSE_NOT_FOUND_MESSAGE + id));
    }

    public void updateCourse(Long id, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setTitle(updatedCourse.getTitle());
            course.setCategory(updatedCourse.getCategory());
            courseRepository.save(course);
        } else {
            throw new IllegalArgumentException(COURSE_NOT_FOUND_MESSAGE + id);
        }
    }

    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(COURSE_NOT_FOUND_MESSAGE + id);
        }
    }

    public Course findById(Course courseId) {
        return null;
    }
}
