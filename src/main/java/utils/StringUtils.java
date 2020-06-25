package utils;

public class StringUtils {

    public static final String EMPTY = "";

    private StringUtils() {
    }

    public static boolean isEmpty(String string) {
        return (string == null || string.isEmpty());
    }

    public static boolean isEnglishLettersOnly(String string) {
        return string.chars().allMatch(Character::isAlphabetic);
    }

    public static String center(String s, int size) {
        char pad = ' ';
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
