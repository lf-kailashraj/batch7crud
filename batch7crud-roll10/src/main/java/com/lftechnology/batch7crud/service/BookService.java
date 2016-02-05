package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.BookDao;
import com.lftechnology.batch7crud.entity.Book;
import com.lftechnology.batch7crud.exception.DataException;

import java.util.List;

/**
 * @Author binodnme
 * Created on 2/3/16
 */
public class BookService {
  BookDao bookDao = new BookDao();

  public void addBook(Book book, Integer copies) throws DataException {
    bookDao.addBook(book, copies);
  }

  public List<Book> fetch(Integer offset, Integer limit) throws DataException {
    return bookDao.fetch(offset, limit);
  }
}
