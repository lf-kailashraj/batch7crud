package com.lftechnology.batch7crud.entity;

import com.lftechnology.batch7crud.utils.annotation.Required;

/**
 * Book class holds the book information and getter & setter methods.
 *
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/17/16
 */
public class Book {
  private Integer id;
  @Required private String name;
  @Required private String author;
  @Required private String publisher;
  @Required private String edition;
  private String isbn;      //International Standard Book Number

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
}
