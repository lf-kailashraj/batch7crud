package com.lftechnology.batch7crud.dao;

import java.util.List;
import com.lftechnology.batch7crud.model.Student;

/**
 * Created by sagarmatha on 1/26/16.
 */
public interface StudentDao {
    public void addStudent(Student student);
    public void deleteStudent(int studentID);
    public void updateStudent(Student student);
    public List<Student> getAllStudents();
}
