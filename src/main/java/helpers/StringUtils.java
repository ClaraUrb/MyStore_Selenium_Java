package helpers;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class StringUtils {

    public static String getRandomBirthdateFormatted() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Faker faker = new Faker();
        return simpleDateFormat.format(faker.date().birthday());
    }

    public static String removeFirstChar(String string) {
        return string.substring(1);
    }

    public static int getIntFromString(String string) {
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }
}
