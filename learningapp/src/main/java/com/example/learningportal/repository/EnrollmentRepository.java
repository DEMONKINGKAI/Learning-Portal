package com.example.learningportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learningportal.entity.Course;
import com.example.learningportal.entity.Enrollment;
import com.example.learningportal.entity.User;

@Repository

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByCourseAndUser(Course course, User user);
}
