package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.StudentDao;
import com.lftechnology.batch7crud.dao.StudentDaoImpl;
import com.lftechnology.batch7crud.model.Student;

import java.util.List;

/**
 * Created by sagarmatha on 1/27/16.
 */
public class StudentService {
    private StudentDao dao=new StudentDaoImpl();

    public Student insert(Student student) {
        return dao.addStudent(student);
    }

    public List<Student> viewList(int page, int limit) {
        return dao.getAllStudents(page, limit);
    }

    public Student viewDetail(int studentID) {
        return dao.getStudentByID(studentID);
    }

    public Student update(Student student) {
        return dao.updateStudent(student);
    }

    public void delete(int id) {
        dao.deleteStudent(id);
    }

    public int countStudents(){
        return dao.countStudents();
    }

    public boolean findRecord(int studentID){
        return dao.findStudent(studentID);
    }
}