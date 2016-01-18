package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.StudentDataAccess;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.model.Student;

import java.util.List;

/**
 * Created by leapfrog on 1/18/16.
 */
public class StudentServices {
    private StudentDataAccess studentDao = new StudentDataAccess();
    public void addNew(Student s) throws DataException {
        studentDao.addNew(s);
    }
    public List<Student> fetch(int page) throws DataException {
        return studentDao.fetch(page);
    }
}
