package helpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberFormatter {

    public static double priceFormatter(String price) {
        return Double.parseDouble(StringUtils.removeFirstChar(price));
    }

    public static String round(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("##.00", DecimalFormatSymbols.getInstance(Locale.US));
        return decimalFormat.format(price);
    }

}
