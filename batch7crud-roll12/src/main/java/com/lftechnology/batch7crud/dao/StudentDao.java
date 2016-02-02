package com.lftechnology.batch7crud.dao;

import java.util.List;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.model.Student;

/**
 * Created by sagarmatha on 1/26/16.
 */
public interface StudentDao {
    Student addStudent(Student student);
    void deleteStudent(int studentID);
    Student updateStudent(Student student);
    List<Student> getAllStudents(int page, int limit);
    Student getStudentByID(int studentID);
    int countStudents();
    boolean findStudent(int studentID);
}
