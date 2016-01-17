package com.lftechnology.batch7crud.entity;

import java.util.Date;

/**
 * @Author binodnme
 * Created on 1/17/16
 */
public class Transaction {
    private Integer id;
    private Date issueDate;
    private Date returnDate;
    private Person person;
    private BookPer bookPer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BookPer getBookPer() {
        return bookPer;
    }

    public void setBookPer(BookPer bookPer) {
        this.bookPer = bookPer;
    }
}
