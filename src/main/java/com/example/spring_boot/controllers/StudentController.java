package com.example.spring_boot.controllers;

import com.example.spring_boot.dto.StudentRequestDTO;
import com.example.spring_boot.dto.StudentDTO;
import com.example.spring_boot.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentRequestDTO studentDTO) {
        return ResponseEntity.status(201).body(studentService.createStudent(studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentById(id, studentRequestDTO));
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<StudentDTO> updateStudentName(@PathVariable String id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentName(id, studentRequestDTO));
    }

    @PatchMapping("/email/{id}")
    public ResponseEntity<StudentDTO> updateStudentEmail(@PathVariable String id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentEmail(id, studentRequestDTO));
    }
}