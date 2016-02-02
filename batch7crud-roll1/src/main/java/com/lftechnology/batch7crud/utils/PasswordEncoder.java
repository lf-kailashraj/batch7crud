package com.lftechnology.batch7crud.utils;

import com.lftechnology.batch7crud.exception.PasswordEncoderException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Class encodes text password into 16 byte hash value
 * <p>
 * Created by kiran on 1/29/16.
 */
public class PasswordEncoder {

  public static final Logger LOGGER = Logger.getLogger(PasswordEncoder.class.getName());

  private PasswordEncoder() {

  }

  public static String encodePassword(String passwordToEncode) throws PasswordEncoderException {

    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(passwordToEncode.getBytes());
      byte[] bytes = md.digest();
      StringBuilder sb = new StringBuilder();
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
      throw new PasswordEncoderException(e.getMessage());
    }
  }
}
