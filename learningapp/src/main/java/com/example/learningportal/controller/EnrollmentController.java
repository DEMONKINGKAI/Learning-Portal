package com.example.learningportal.controller;



import com.example.learningportal.entity.User;
import com.example.learningportal.service.EnrollmentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollUser(@RequestParam Long userId) {
        boolean enrolled = enrollmentService.enrollUser(userId);
        if (enrolled) {
            log.info("User Enrolled");
            return ResponseEntity.ok("User enrolled successfully!");
        } else {
            log.info("User Not Enrolled");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to enroll user.");
        }
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdrawEnrollment(@RequestParam Long userId) {
        boolean withdrawn = enrollmentService.withdrawEnrollment(userId);
        if (withdrawn) {
            log.info("User Enrollment Withdrawn");
            return ResponseEntity.ok("User enrollment withdrawn successfully!");
        } else {
            log.info("User Enrollment Withdrawal Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to withdraw user enrollment.");
        }
    }
}
