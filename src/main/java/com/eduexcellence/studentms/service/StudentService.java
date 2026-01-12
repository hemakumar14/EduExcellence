package com.eduexcellence.studentms.service;

import com.eduexcellence.studentms.entity.Student;
import com.eduexcellence.studentms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Logic to fetch all students [cite: 18]
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Logic to fetch a single student [cite: 19]
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // 3. Logic to create a new student [cite: 20]
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // 4. Logic to delete a student [cite: 21]
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // 5. Logic to update a student [cite: 22]
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setAddress(studentDetails.getAddress());
            student.setGrade(studentDetails.getGrade());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }
}