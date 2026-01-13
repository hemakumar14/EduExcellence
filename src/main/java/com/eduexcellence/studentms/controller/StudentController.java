package com.eduexcellence.studentms.controller;

import com.eduexcellence.studentms.entity.Student;
import com.eduexcellence.studentms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String FEES_URL = "http://localhost:8081/fees";

    // Call feesms: Fetch fees for a specific student
    @GetMapping("/{id}/fee-records")
    public List<Object> getFeeRecords(@PathVariable Long id) {
        return restTemplate.getForObject(FEES_URL + "/" + id, List.class);
    }

    // Call feesms: Process a payment for a student
    @PostMapping("/{id}/pay-fees")
    public Object processPayment(@PathVariable Long id, @RequestBody Object feeRequest) {
        return restTemplate.postForObject(FEES_URL, feeRequest, Object.class);
    }

    // 1. An API to fetch all students [cite: 18]
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. An API to fetch a single student [cite: 19]
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // 3. An API to create a new student [cite: 20]
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // 4. An API to delete a student [cite: 21]
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "Student with ID " + id + " has been deleted.";
    }

    // 5. An API to update a student [cite: 22]
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            Student existingStudent = studentData.get();
            existingStudent.setName(studentDetails.getName());
            existingStudent.setAddress(studentDetails.getAddress());
            existingStudent.setGrade(studentDetails.getGrade());
            return studentRepository.save(existingStudent);
        } else {
            return null; // Or throw an exception
        }
    }
}