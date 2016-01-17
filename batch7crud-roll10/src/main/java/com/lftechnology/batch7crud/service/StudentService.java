package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.PersonDao;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.dao.StudentDao;

import java.util.List;

/**
 * @Author binodnme
 * Created on 1/14/16
 */
public class StudentService {
    private StudentDao studentDao = new StudentDao();
    private PersonDao personDao = new PersonDao();

    public List<Student> fetchData(){
        return studentDao.fetchData(10);
    }

    public void add(Student student){
        if(personDao.add(student.getPerson())){

            if(!studentDao.add(student)){
                personDao.delete(student.getPerson());
            }
        }


    }

    public void delete(Student student){
        studentDao.delete(student);
    }

    public void edit(Student student){
        studentDao.edit(student);
    }
}
