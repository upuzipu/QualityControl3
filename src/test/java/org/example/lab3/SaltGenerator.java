package org.example.lab3;

import java.util.Random;

public class SaltGenerator {
    private static final String SALT_CHARS = "1234567890";

    public static String generateSalt() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }
        return salt.toString();
    }
}
