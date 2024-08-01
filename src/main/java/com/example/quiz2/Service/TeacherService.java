package com.example.quiz2.Service;

import com.example.quiz2.Model.Student;
import com.example.quiz2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean updateTeacher(int id,Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(int id){
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.remove(i);
                return true;
            }
        }
        return false;

    }

   public Teacher getTeacherID(int id){
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                return teachers.get(i);
            }
        }
        return null;
   }

   public ArrayList<Teacher> getSalary(int salary){
      ArrayList list = new ArrayList();
      for (int i = 0; i < teachers.size(); i++) {
          if (teachers.get(i).getSalary() == salary){
              if(teachers.get(i).getSalary() >= salary){
                  list.add(teachers.get(i));
              }
          }
      }
      return list;

   }



}
