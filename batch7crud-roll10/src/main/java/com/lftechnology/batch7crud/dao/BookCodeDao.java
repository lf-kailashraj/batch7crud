package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.entity.Book;
import com.lftechnology.batch7crud.entity.BookCode;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author binodnme
 * Created on 2/3/16
 */
public class BookCodeDao {
  private static final Logger LOGGER = Logger.getLogger(BookCodeDao.class.getName());
  private static final String UPDATE_BOOK_CODE= "UPDATE book_code SET availability=? WHERE id=?";
  private static final String GET_BOOK_COUNT_BY_ID = "select count(*) as total from book_code where book_id=?";
  private static final String GET_AVAIL_BOOK_COUNT_BY_ID = "select count(*) as total from book_code where book_id=? and book_code.availability=TRUE ";
  private static final String INSERT_INTO_BOOK_CODE = "INSERT INTO book_code(book_code, book_id, availability) VALUES (?, ?, ?)";


  public void addBookCode(Book book, Integer copies, Connection conn) throws DataException {
    List<String> bookCodes = generateBookCode(book, copies);

    for(String code: bookCodes){
      BookCode bookCode = new BookCode();
      bookCode.setCode(code);
      bookCode.setIsAvailable(true);
      bookCode.setBook(book);

      insert(bookCode, conn);
    }
  }

  public void insert(BookCode bookCode, Connection conn) throws DataException {
    try (PreparedStatement ps = conn.prepareStatement(INSERT_INTO_BOOK_CODE, Statement.RETURN_GENERATED_KEYS)) {

      ps.setString(1, bookCode.getCode());
      ps.setInt(2, bookCode.getBook().getId());
      ps.setBoolean(3, bookCode.getIsAvailable());
      ps.executeUpdate();


    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public void update(BookCode bookCode) throws DataException {
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_BOOK_CODE)
    ) {
      ps.setBoolean(1, bookCode.getIsAvailable());
      ps.executeUpdate();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }


  public Integer getBookCountById(Integer bookId) throws DataException {
    ResultSet rs;
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BOOK_COUNT_BY_ID)
    ) {
      ps.setInt(1, bookId);
      rs = ps.executeQuery();

      if (rs.next()) {
        return rs.getInt("total");
      } else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public Integer getAvailableBookCountById(Integer bookId) throws DataException {
    ResultSet rs;
    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_AVAIL_BOOK_COUNT_BY_ID)
    ) {
      ps.setInt(1, bookId);
      rs = ps.executeQuery();

      if (rs.next()) {
        return rs.getInt("total");
      } else {
        return 0;
      }
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

  public List<String> generateBookCode(Book book, Integer copies){
    List<String> bookCodes = new ArrayList<>();

    for(int i=1; i<= copies; i++){
      String[] bookTokens = book.getName().trim().split(" ");

      String code = book.getId().toString();
      for(String token : bookTokens){
        code += Character.toString(token.charAt(0));
      }
      code += Integer.toString(i);

      bookCodes.add(code);
    }

    return bookCodes;
  }
}
