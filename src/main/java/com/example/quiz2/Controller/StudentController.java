package com.example.quiz2.Controller;

import com.example.quiz2.Api.ApiResponse;
import com.example.quiz2.Model.Student;
import com.example.quiz2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ArrayList<Student> getStudent() {
        return studentService.getStudents();

    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(student);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id ,@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body("student not updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body("student not deleted");
    }

    @GetMapping("/student/{name}")
    public ResponseEntity getStudentName(@PathVariable String name) {
        Student students = studentService.studentName(name);
        if (students == null) {
          return ResponseEntity.status(400).body("student not found");
        }
        return ResponseEntity.status(200).body(students);
    }

    @GetMapping("/major")
    public ResponseEntity getMajor() {
        return ResponseEntity.status(200).body(studentService.getStudentsMajor());

    }





}
