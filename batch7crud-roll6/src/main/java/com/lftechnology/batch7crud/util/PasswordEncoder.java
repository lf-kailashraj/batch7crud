package com.lftechnology.batch7crud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordEncoder {
  public static final Logger LOGGER = Logger.getLogger(PasswordEncoder.class.getName());

  private PasswordEncoder() {

  }

  public static String encodePassword(String passwordToEncode){
    StringBuilder sb =null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(passwordToEncode.getBytes());
      byte[] bytes = md.digest();
      sb = new StringBuilder();
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
      }
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return sb.toString();
  }

}
