package com.lftechnology.batch7crud.factory;

import com.lftechnology.batch7crud.entity.Book;
import com.lftechnology.batch7crud.entity.Student;
import com.lftechnology.batch7crud.exception.ValidationException;
import com.lftechnology.batch7crud.utils.DateUtils;
import com.lftechnology.batch7crud.utils.annotation.FieldValidator;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.lftechnology.batch7crud.constant.EntityConstant.*;
import static com.lftechnology.batch7crud.constant.EntityConstant.ROLL;

/**
 * @Author binodnme
 * Created on 2/3/16
 */
public class BookFactory {
  private static final Logger LOGGER = Logger.getLogger(BookFactory.class.getName());
  private static final String COPIES = "copies";

  public static Book createBook(Map<String, String> params, Map<String, String> errors){

    String name = params.get(NAME);
    String author = params.get(AUTHOR);
    String publisher = params.get(PUBLISHER);
    String edition = params.get(EDITION);

    Book book = new Book();
    book.setName(name);
    book.setAuthor(author);
    book.setPublisher(publisher);
    book.setEdition(edition);

    try {
      FieldValidator.validate(book);
    } catch (ValidationException e) {
      errors.putAll(e.getErrors());
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

    String copies = params.get(COPIES);
    try {
      Integer.parseInt(copies);
    }catch (NumberFormatException e){
      errors.put(COPIES, "should be positive integer");
    }

    return book;

  }
}
