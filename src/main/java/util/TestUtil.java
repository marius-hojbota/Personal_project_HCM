package util;

import java.util.Random;

public class TestUtil {
    public static String generateRandomEmail() {
        Random random = new Random();
        String email = "hari" + random.nextLong() + "@email.com";
        System.out.println("User email is: " + email);
        return email;
    }
}
