package io.kante.jofoo.util;

/**
 * Created by moh on 8/15/16.
 */
public final class Assert
{
    private Assert(){};

    public static void notNull(Object obj, String error) {
        if (obj == null) {
            throw new Error(error);
        }
    }

    public static void notNull(Object obj, Error error) {
        if (obj == null) {
            throw error;
        }
    }

    public static void isTrue(boolean bool, String msg) {
        if (!bool) {
            throw new Error(msg);
        }
    }

    public static void isTrue(boolean bool, Error error) {
        if (!bool) {
            throw error;
        }
    }

    public static  void required(String obj, Error error) {

        notNull(obj,error);

        if (obj.trim().equals("")) {
            throw error;
        }
    }

    public static  void required(String obj, String error) {

        notNull(obj,error);

        if (obj.equals("")) {
            throw new Error(error);
        }
    }
}
