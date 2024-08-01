package com.example.quiz2.Controller;

import com.example.quiz2.Api.ApiResponse;
import com.example.quiz2.Model.Student;
import com.example.quiz2.Model.Teacher;
import com.example.quiz2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ArrayList<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }
   @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("teacher added successfully"));
   }
   @PutMapping("/update/{id}")
   public ResponseEntity updateTeacher(@PathVariable int id ,@Valid @RequestBody Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=teacherService.updateTeacher(id,teacher);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("teacher updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher not updated"));
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteTeacher(@PathVariable int id){
        boolean isDeleted=teacherService.deleteTeacher(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("teacher deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher not deleted"));
   }

   @GetMapping("/get/teacher/{id}")
   public ResponseEntity getTeacher(@PathVariable int id){
        Teacher teachers= teacherService.getTeacherID(id);
        if(teachers==null){
            return ResponseEntity.status(400).body(new ApiResponse("teacher not found"));
        }
        return ResponseEntity.status(200).body(teachers);
   }


@GetMapping("/get/salary/")
   public ResponseEntity takeSalary(@PathVariable int salary){
     return ResponseEntity.status(200).body(teacherService.getSalary(salary));
}

}
