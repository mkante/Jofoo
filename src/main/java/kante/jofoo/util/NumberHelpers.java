package kante.jofoo.util;

import java.math.BigDecimal;

/**
 * Created by moh on 8/8/16.
 */
public abstract class NumberHelpers
{

    public static Integer parseInt(String val) {
        return Integer.valueOf(val);
    }

    public static Double parseDouble(String val) {
        return Double.valueOf(val);
    }

    public static Float parseFloat(String val) {
        return Float.valueOf(val);
    }

    public static Long parseLong(String val) {
        return Long.valueOf(val);
    }

    public static BigDecimal parseBigDecimal(String val) {
        return BigDecimal.valueOf(parseDouble(val));
    }
}
