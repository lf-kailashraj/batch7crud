package com.lftechnology.batch7crud.Utils;

import com.lftechnology.batch7crud.exception.DataException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by leapfrog on 1/27/16.
 */
public class Utilities {
  private Utilities(){

  }

  public static String getMD5(String input) throws DataException {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger number = new BigInteger(1, messageDigest);
      String hashtext = number.toString(16);
      // Now we need to zero pad it if you actually want the full 32 chars.
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    }
    catch (NoSuchAlgorithmException e) {
      throw new DataException();
    }
  }
}
