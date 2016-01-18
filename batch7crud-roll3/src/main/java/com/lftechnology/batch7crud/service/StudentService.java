package com.lftechnology.batch7crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lftechnology.batch7crud.dao.StudentDAO;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.DataException;

public class StudentService {
	public void insert(Student student) throws DataException {
		StudentDAO stdDAO = new StudentDAO();
		stdDAO.insert(student);
	}

	public HashMap<Integer, List> fetch(int page) throws DataException {
		StudentDAO stdDAO = new StudentDAO();
		List<Student> stdList = new ArrayList<Student>();
		HashMap<Integer, List> hm = new HashMap<Integer, List>();
		hm = stdDAO.fetch(page);
		int count;
		return hm;
	}

	public Student getStudentById(int id) throws DataException {
		StudentDAO stdDAO = new StudentDAO();
		Student student = new Student();
		student = stdDAO.getStudentById(id);
		return student;
	}

	public void edit(Student student, int id) throws DataException {
		StudentDAO stdDAO = new StudentDAO();
		stdDAO.edit(student, id);
	}

	public void delete(int id) throws DataException {
		// TODO Auto-generated method stub
		StudentDAO stdDAO = new StudentDAO();
		stdDAO.delete(id);
	}
}
