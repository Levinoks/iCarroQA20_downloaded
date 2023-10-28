package utils;

import java.util.Random;

public class RandomUtils {

    Random random = new Random();

    public String generateEmail(int length) {
        String[] domains = { "gmail.com", "yahoo.com", "outlook.com", "example.com", "domain.com" };
        String domain = domains[random.nextInt(domains.length)];
        return (generateString(length) + "@" + domain);
    }

    public String generateString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        char[] randomString = new char[length];
        int index = 0;
        int charLength = characters.length();
        for(int i = 0; i < length; i++) {
            index = random.nextInt(charLength);
            randomString[i] = characters.charAt(index);
        }
        return new String(randomString); // aksdhjTG78
    }
    public String generatePassword(int length) {
        String[] specChars = {"$", "!", "#", "@"};
        String chars = specChars[random.nextInt(specChars.length)];

        return generateString(length / 2) + chars + generateString(length / 2).toUpperCase();
    }
}
