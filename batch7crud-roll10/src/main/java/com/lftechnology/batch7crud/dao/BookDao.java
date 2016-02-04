package com.lftechnology.batch7crud.dao;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.entity.Book;
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
public class BookDao {
  private static final Logger LOGGER = Logger.getLogger(BookDao.class.getName());
  private static final String FETCH_BOOK_LIMIT_OFFSET = "SELECT * FROM book LIMIT ? OFFSET ?";
  private static final String INSERT_INTO_BOOK = "INSERT INTO book (name, author, publisher, edition) VALUES (?, ?, ?, ?)";

  public List<Book> fetch(Integer offset, Integer limit) throws DataException {
    List<Book> bookList = new ArrayList<>();
    ResultSet bookResult;

    try (Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(FETCH_BOOK_LIMIT_OFFSET)
    ) {

      ps.setInt(1, limit);
      ps.setInt(2, offset);
      bookResult = ps.executeQuery();

      while (bookResult.next()) {
        Integer id = bookResult.getInt(EntityConstant.ID);
        String name = bookResult.getString(EntityConstant.NAME);
        String author = bookResult.getString(EntityConstant.AUTHOR);
        String publisher = bookResult.getString(EntityConstant.PUBLISHER);
        String edition = bookResult.getString(EntityConstant.EDITION);
        String isbn = bookResult.getString(EntityConstant.ISBN);

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setEdition(edition);
        book.setIsbn(isbn);

        bookList.add(book);
      }

      return bookList;

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }


  public void addBook(Book book, Integer copies) throws DataException {
    try (Connection conn = DbUtils.getConnection()) {
      conn.setAutoCommit(false);

      Integer bookId = insert(book, conn);

      book.setId(bookId);
      BookCodeDao bookCodeDao = new BookCodeDao();
      bookCodeDao.addBookCode(book, copies, conn);

      conn.commit();

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

  public Integer insert(Book book, Connection conn) throws DataException {
    try (PreparedStatement ps = conn.prepareStatement(INSERT_INTO_BOOK, Statement.RETURN_GENERATED_KEYS)) {

      ps.setString(1, book.getName());
      ps.setString(2, book.getAuthor());
      ps.setString(3, book.getPublisher());
      ps.setString(4, book.getEdition());

      ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();
      rs.next();
      return rs.getInt(1);

    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException(e.getMessage());
    }
  }

}
