package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjay on 1/14/16.
 */
public class StudentService {
  private StudentDAO stdDAO = new StudentDAO();

  public void save(Student s) throws DataException {
    stdDAO.insert(s);
  }

  public List<Student> fetch(int page, int limit) throws DataException {
    return stdDAO.fetch(page, limit);
  }

  public void delete(int id) throws DataException {
    stdDAO.delete(id);
  }

  public Student fetchById(int id) throws DataException {
    return stdDAO.fetchById(id);
  }

  public void edit(Student s) throws DataException {
    stdDAO.edit(s);
  }

  public int studentCount() throws DataException {
    return stdDAO.studentCount();
  }
}
