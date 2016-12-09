package com.lftechnology.batch7crud.util;

import com.lftechnology.batch7crud.exception.DataException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sanjay on 1/29/16.
 */
public class DataHash {
  private static final Logger LOGGER = Logger.getLogger(DataHash.class.getName());

  public static String getMD5(String input) throws DataException {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger number = new BigInteger(1, messageDigest);
      String hashText = number.toString(16);
      // Now we need to zero pad it if you actually want the full 32 chars.
      while (hashText.length() < 32) {
        hashText = "0" + hashText;
      }
      return hashText;
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new DataException();
    }
  }

}
