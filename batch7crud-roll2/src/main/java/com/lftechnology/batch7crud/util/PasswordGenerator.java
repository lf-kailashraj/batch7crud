package com.lftechnology.batch7crud.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Romit Amgai <romitamgai@lftechnology.com> on 1/27/16.
 */
public class PasswordGenerator {
  private PasswordGenerator() {
  }

  public static String getMD5(String input) throws NoSuchAlgorithmException {
    MessageDigest mdEnc = MessageDigest.getInstance("MD5");
    mdEnc.update(input.getBytes(), 0, input.length());
    return new BigInteger(1, mdEnc.digest()).toString(16);
  }
}
