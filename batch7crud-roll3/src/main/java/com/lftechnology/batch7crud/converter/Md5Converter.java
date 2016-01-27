package com.lftechnology.batch7crud.converter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.exception.DataException;

public class Md5Converter {
  private static final Logger LOGGER = Logger.getLogger(Md5Converter.class.getName());

  public String convert(String password) throws DataException {
    String hashText = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");

      byte[] messageDigest = md.digest(password.getBytes());
      BigInteger number = new BigInteger(1, messageDigest);
      hashText = number.toString(16);
      while (hashText.length() < 32) {
        hashText = "0" + password;
      }
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);

      throw new DataException(e.getMessage());
    }
    return hashText;

  }
}
