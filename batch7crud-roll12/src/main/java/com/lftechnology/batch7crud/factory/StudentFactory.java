package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.model.Student;

/**
 * Created by sagarmatha on 2/5/16.
 */
public interface StudentFactory {
  Student createStudent(String firstName, String lastName, int age, String address );
  Student updateStudent(int id, String firstName, String lastName, int age, String address );
}
