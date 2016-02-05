package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.BookCodeDao;
import com.lftechnology.batch7crud.exception.DataException;

/**
 * @Author binodnme
 * Created on 2/3/16
 */
public class BookCodeService {
  BookCodeDao bookCodeDao = new BookCodeDao();

  public Integer getBookCountById(Integer bookId) throws DataException {
    return bookCodeDao.getBookCountById(bookId);
  }

  public Integer getAvailableBookCountById(Integer bookId) throws DataException {
    return bookCodeDao.getAvailableBookCountById(bookId);
  }
}
