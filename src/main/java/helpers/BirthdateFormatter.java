package helpers;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class BirthdateFormatter {

    public static String getRandomBirthdateFormatted() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Faker faker = new Faker();
        return simpleDateFormat.format(faker.date().birthday());
    }
}
