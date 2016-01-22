package com.lftechnology.batch7crud.services;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;
import java.util.List;

/**
 * Created by sanjay on 1/14/16.
 */

public class StudentService {
  private StudentDAO stdDAO = new StudentDAO();

  public Student save(Student student) throws DataException {
    return stdDAO.insert(student);
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

  public Student edit(Student student) throws DataException {
    return stdDAO.edit(student);
  }

  public int studentCount() throws DataException {
    return stdDAO.studentCount();
  }
}
