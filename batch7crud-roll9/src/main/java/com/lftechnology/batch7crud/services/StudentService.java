package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;

/**
 * Created by sanjay on 1/14/16.
 */
public class StudentService {
    private StudentDAO stdDAO = new StudentDAO();

    public void save(Student s){
       stdDAO.insert(s);
        System.out.println("in studectservice save");
    }
}
