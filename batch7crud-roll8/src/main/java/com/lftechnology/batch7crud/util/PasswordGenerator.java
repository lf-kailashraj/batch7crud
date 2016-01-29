package com.lftechnology.batch7crud.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Grishma Shrestha grishmashrestha@lftechnology.com on 1/29/16.
 */
public class PasswordGenerator {
  private PasswordGenerator() {
  }

  public static String hashUsingMD5 (String val) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(val.getBytes(), 0, val.length());
    return new BigInteger(1, md.digest()).toString(16);
  }
}
