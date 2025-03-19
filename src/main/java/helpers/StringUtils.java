package helpers;

import com.github.javafaker.Faker;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

    public static double priceFormatter(String price) {
        return Double.parseDouble(removeFirstChar(price));
    }

    public static double round(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("##.00", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(decimalFormat.format(price));
    }
}
