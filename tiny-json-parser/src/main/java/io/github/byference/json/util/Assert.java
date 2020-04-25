package io.github.byference.json.util;

/**
 * Assert
 *
 * @author byference
 * @since 2020-04-19
 */
public class Assert {

    public static void isNotNull(String str, String errorMessage) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void isNotNull(Object[] array, String errorMessage) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
