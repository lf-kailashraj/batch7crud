package com.lftechnology.batch7crud.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Converter {

  public String convert(String password) throws NoSuchAlgorithmException {
    String hashText;
    MessageDigest md = MessageDigest.getInstance("MD5");

    byte[] messageDigest = md.digest(password.getBytes());
    BigInteger number = new BigInteger(1, messageDigest);
    hashText = number.toString(16);
    while (hashText.length() < 32) {
      hashText = "0" + password;
    }
    return hashText;

  }
}
