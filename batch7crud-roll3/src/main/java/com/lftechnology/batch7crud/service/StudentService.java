package com.lftechnology.batch7crud.service;

import java.util.List;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

public class StudentService {
	private StudentDAO stdDAO = new StudentDAO();

	public void insert(Student student) throws DataException {
		stdDAO.insert(student);
	}

	public List<Student> fetch(int page, int pageSize) throws DataException {
		return stdDAO.fetch(page, pageSize);
	}

	public Student fetchStudentById(int id) throws DataException {
		return stdDAO.fetchStudentById(id);
	}

	public int fetchTotal() throws DataException {
		return stdDAO.fetchTotal();
	}

	public void edit(Student student, int id) throws DataException {
		stdDAO.edit(student, id);
	}

	public void delete(int id) throws DataException {
		stdDAO.delete(id);
	}
}
