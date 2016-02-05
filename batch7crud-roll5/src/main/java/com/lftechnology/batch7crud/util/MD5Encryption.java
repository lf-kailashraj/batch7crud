package com.lftechnology.batch7crud.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lftechnology.batch7crud.dao.EmployeeDAOImpl;
import com.lftechnology.batch7crud.exception.EncryptionException;

public class MD5Encryption {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class.getName());

    private MD5Encryption() {

    }

    public static String getMD5(String input) throws NoSuchAlgorithmException, EncryptionException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // pad zero if the length is less than 32
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new EncryptionException(e.getMessage());
        }
    }
}
