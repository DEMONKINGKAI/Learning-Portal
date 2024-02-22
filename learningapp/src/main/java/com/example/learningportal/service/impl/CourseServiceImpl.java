package com.example.learningportal.service.impl;

import com.example.learningportal.UnauthorizedException;
import com.example.learningportal.entity.Course;
import com.example.learningportal.entity.User;
import com.example.learningportal.repository.CourseRepository;
import com.example.learningportal.service.CourseService;
import com.example.learningportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    @Override
    public Course createCourse(User user, Course course) {
        if (!UserService.isAuthor(user)) {
            throw new UnauthorizedException("Only authors can create courses.");
        }
        course.setAuthor(user);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
