package com.lftechnology.batch7crud.util;

/**
 * Created by pratishshr on 1/27/16.
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
  private Md5() {

  }
  public static String getMD5(String input) throws NoSuchAlgorithmException{
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger number = new BigInteger(1, messageDigest);
      String hashText = number.toString(16);
     
      while (hashText.length() < 32) {
        hashText = "0" + hashText;
      }
      return hashText;
  }

}