package com.lftechnology.batch7crud.entity;

import java.util.Date;

/**
 * @Author Binod Shrestha <binodshrestha@lftechnology.com>
 * Created on 1/17/16
 */
public class Fine {
  private Integer id;
  private Integer amountPerDay;
  private Date applyDate;
  private Date endDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAmountPerDay() {
    return amountPerDay;
  }

  public void setAmountPerDay(Integer amountPerDay) {
    this.amountPerDay = amountPerDay;
  }

  public Date getApplyDate() {
    return applyDate;
  }

  public void setApplyDate(Date applyDate) {
    this.applyDate = applyDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
