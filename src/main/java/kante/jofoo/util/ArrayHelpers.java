package kante.jofoo.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by moh on 8/8/16.
 */
public abstract class ArrayHelpers
{
    static public String[] arrayObjectToString(Object... obj) {
        String[] strArray = new String[obj.length];

        for (int i=0; i < obj.length;i++) {
            strArray[i] = obj[i]+"";
        }
        return strArray;
    }

    static public String[] jsonnableObjectToString(Jsonnable... obj) {
        String[] strArray = new String[obj.length];

        for (int i=0; i < obj.length;i++) {
            strArray[i] = obj[i].toJson();
        }
        return strArray;
    }

    static public String[] split(String str, String joiner) {
        if (str == null) {
            return null;
        }

        String[] parts = str.trim().split(joiner);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim() ;
        }

        return parts;
    }

    public static Object[] merge(final Object[] arrayA, final Object... arrayB) {
        int size = arrayA.length + arrayB.length;
        Object[] lst1 = new Object[size];

        int i=0;
        for (; i < arrayA.length; i++) {
            lst1[i] = arrayA[i];
        }

        for (int j=0; j < arrayB.length; j++) {
            lst1[j+i] = arrayB[j];
        }

        return lst1;
    }
}
