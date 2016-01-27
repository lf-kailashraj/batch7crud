package com.lftechnology.batch7crud.service;

import com.lftechnology.batch7crud.dao.AdminDao;
import com.lftechnology.batch7crud.exception.DataException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/27/16.
 */
public class AdminService {
  private AdminDao adminDao;

  public AdminService() {
    adminDao = new AdminDao();
  }

  public boolean isValidAdmin(String userName, String password) throws DataException, NoSuchAlgorithmException { //NOSONAR
    MessageDigest mdEnc = MessageDigest.getInstance("MD5");
    mdEnc.update(password.getBytes(), 0, password.length());
    String hashedPassword = new BigInteger(1, mdEnc.digest()).toString(16); // Hash value
    return adminDao.isValid(userName, hashedPassword);
  }
}
