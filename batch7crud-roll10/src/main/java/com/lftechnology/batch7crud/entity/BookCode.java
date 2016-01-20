package com.lftechnology.batch7crud.entity;

/**
 * BookCode is used to identify each and every book uniquely.
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/17/16
 */
public class BookCode {
  private Integer id;
  private String code;
  private Boolean isAvailable;
  private Book book;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
