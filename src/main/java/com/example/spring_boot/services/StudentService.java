package com.example.spring_boot.services;

import com.example.spring_boot.dto.StudentRequestDTO;
import com.example.spring_boot.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudent(String id);

    StudentDTO createStudent(StudentRequestDTO studentRequestDTO);

    void deleteStudentById(String id);

    StudentDTO updateStudentById(String id, StudentRequestDTO studentRequestDTO);

    StudentDTO updateStudentName(String id, StudentRequestDTO studentRequestDTO);

    StudentDTO updateStudentEmail(String id, StudentRequestDTO studentRequestDTO);
}
