package helpers;

public class NumberFormatter {

    public static double priceFormatter(String price) {
        return Double.parseDouble(StringUtils.removeFirstChar(price));
    }
}
