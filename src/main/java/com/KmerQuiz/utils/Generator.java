package com.KmerQuiz.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class Generator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = UPPER + DIGITS;
    private static final SecureRandom RANDOM = new SecureRandom();

    private Generator() {
        // Private constructor to prevent instantiation
    }


    public static String generateUserId() {
        return "USR" + randomString(DIGITS, 5);
    }


    public static String generatePartyCode() {
        return "KQ" + randomString(ALPHANUMERIC, 4);
    }


    public static String generatePin(int length) {
        return randomString(DIGITS, length);
    }


    public static String generateToken(int length) {
        return randomString(ALPHANUMERIC, length);
    }


    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }


    public static int generateRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return min + RANDOM.nextInt((max - min) + 1);
    }

    private static String randomString(String characters, int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
