package com.lftechnology.batch7crud.controller;

import com.lftechnology.batch7crud.constant.EntityConstant;
import com.lftechnology.batch7crud.constant.PageConstant;
import com.lftechnology.batch7crud.constant.RequestMethod;
import com.lftechnology.batch7crud.entity.Book;
import com.lftechnology.batch7crud.exception.DataException;
import com.lftechnology.batch7crud.factory.BookFactory;
import com.lftechnology.batch7crud.service.BookCodeService;
import com.lftechnology.batch7crud.service.BookService;
import com.lftechnology.batch7crud.utils.requestmapper.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 2/3/16
 */
@WebServlet("/books/*")
public class BookController extends HttpServlet {
  private static final Logger LOGGER = Logger.getLogger(BookController.class.getName());
  private static final String ERRORS = "errors";
  private static final String COPIES = "copies";

  private static BookService bookService = new BookService();


  @RequestMapping(value = "list", method = RequestMethod.GET)
  private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // NOSONAR

    List<Book> bookList;
    List<Map> bookMap = new ArrayList<>();
    try {
      bookList = bookService.fetch(0, 100);
      for(Book book : bookList){
        Map<String, String> map = getMapFromObject(book);

        BookCodeService bookCodeService = new BookCodeService();
        Integer totalBooks = bookCodeService.getBookCountById(book.getId());
        Integer availableBooks = bookCodeService.getAvailableBookCountById(book.getId());

        map.put("total", totalBooks.toString());
        map.put("available", availableBooks.toString());
        bookMap.add(map);
      }
    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);
    }

    req.setAttribute("bookList", bookMap);

    req.getServletContext().getRequestDispatcher(PageConstant.BOOK_LIST_VIEW).forward(req, resp);
  }

  @RequestMapping(value = "add", method = RequestMethod.GET)
  private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // NOSONAR
    req.getServletContext().getRequestDispatcher(PageConstant.ADD_BOOK_VIEW).forward(req, resp);
  }

  @RequestMapping(value = "add", method = RequestMethod.POST)
  private void addProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException { // NOSONAR
    try {
      Map<String, String> errors = new HashMap<>();
      Map<String, String> paramMap = buildParamMap(req);
      Book book = BookFactory.createBook(paramMap, errors);

      if (errors.isEmpty()) {
        bookService.addBook(book, Integer.parseInt(paramMap.get(COPIES)));
        resp.sendRedirect(req.getContextPath() + PageConstant.BOOK_LIST_URL);
      } else {
        req.setAttribute(ERRORS, errors);
        add(req, resp);
      }

    } catch (DataException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new ServletException(PageConstant.INTERNAL_SERVER_ERROR);
    }
  }

  private Map<String, String> buildParamMap(HttpServletRequest req) {
    String name = req.getParameter(EntityConstant.NAME);
    String author = req.getParameter(EntityConstant.AUTHOR);
    String publisher = req.getParameter(EntityConstant.PUBLISHER);
    String edition = req.getParameter(EntityConstant.EDITION);
    String copies = req.getParameter(COPIES);

    Map<String, String> paramMap = new HashMap<>();
    paramMap.put(EntityConstant.NAME, name);
    paramMap.put(EntityConstant.AUTHOR, author);
    paramMap.put(EntityConstant.PUBLISHER, publisher);
    paramMap.put(EntityConstant.EDITION, edition);
    paramMap.put(COPIES, copies);

    return paramMap;
  }

  private Map<String, String> getMapFromObject(Book book){
    Map<String, String> map = new HashMap<>();
    map.put(EntityConstant.NAME, book.getName());
    map.put(EntityConstant.AUTHOR, book.getAuthor());
    map.put(EntityConstant.PUBLISHER, book.getPublisher());
    map.put(EntityConstant.EDITION, book.getEdition());

    return map;
  }
}
