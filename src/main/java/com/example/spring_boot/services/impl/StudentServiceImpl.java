package com.example.spring_boot.services.impl;

import com.example.spring_boot.dto.StudentRequestDTO;
import com.example.spring_boot.dto.StudentDTO;
import com.example.spring_boot.entities.Student;
import com.example.spring_boot.repositories.StudentRepository;
import com.example.spring_boot.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        var students = studentRepo.findAll();

        return students
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    @Override
    public StudentDTO getStudent(String id) {
        var student = studentRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found with this Id"));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createStudent(StudentRequestDTO studentRequestDTO) {
        var newStudent = modelMapper.map(studentRequestDTO, Student.class);
        var addedStudent = studentRepo.save(newStudent);
        return modelMapper.map(addedStudent, StudentDTO.class);
    }

    @Override
    public void deleteStudentById(String id) {
        if (!studentRepo.existsById(id)) {
             throw new NoSuchElementException("Student with ID " + id + " not found");
        }

        studentRepo.deleteById(id);
    }

    @Override
    public StudentDTO updateStudentById(String id, StudentRequestDTO studentRequestDTO) {
        var student = studentRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Student with ID " + id + " not found"));
        modelMapper.map(studentRequestDTO, student);

        return modelMapper.map(studentRepo.save(student), StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudentName(String id, StudentRequestDTO studentRequestDTO) {
        var student = studentRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Student with ID " + id + " not found"));
        student.setName(studentRequestDTO.getName());
        return modelMapper.map(studentRepo.save(student), StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudentEmail(String id, StudentRequestDTO studentRequestDTO) {
        var student = studentRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Student with ID " + id + " not found"));
        student.setEmail(studentRequestDTO.getEmail());
        return modelMapper.map(studentRepo.save(student), StudentDTO.class);
    }
}
