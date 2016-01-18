package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.PersonDao;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.dao.StudentDao;
import com.lftechnology.batch7crud.exception.DataException;

import java.util.List;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
public class StudentService {
    private StudentDao studentDao = new StudentDao();

    public List<Student> fetch() throws DataException {
        return studentDao.fetch(10);
    }

    public void insert(Student student) throws DataException {
        studentDao.insert(student);
    }

    public void delete(Student student){
        studentDao.delete(student);
    }

    public void update(Student student) throws DataException {
        studentDao.update(student);
    }

    public Student getStudentById(Integer id) throws DataException {
        return studentDao.getStudentById(id);
    }
}
