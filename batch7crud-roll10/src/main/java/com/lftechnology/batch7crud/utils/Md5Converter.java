package com.lftechnology.batch7crud.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author binodnme
 * Created on 1/28/16
 */
public class Md5Converter {

  private Md5Converter(){
  }

  public static String getHashedText(String text) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] encodedPassword = md.digest(text.getBytes());

    StringBuffer sb = new StringBuffer();
    for (byte anEncodedPassword : encodedPassword)
      sb.append(Integer.toString((anEncodedPassword & 0xff) + 0x100, 16).substring(1));

    return sb.toString();
  }
}
