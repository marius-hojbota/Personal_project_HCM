package util;

import java.util.Random;

public class TestUtil {
    public static String generateRandomEmail() {
        Random random = new Random();
        String email = "hari" + random.nextLong() + "@email.com";
        System.out.println("User email is: " + email);
        return email;
    }
    public static String generateRandomTelephoneNumber() {
        Random rand = new Random();
        long randomNumber = (long)(rand.nextDouble()*10000000000L);
        System.out.println("New telephone number is: " + randomNumber);
        String randomTelephoneNumber = "" + randomNumber;
        return randomTelephoneNumber;
    }
}
