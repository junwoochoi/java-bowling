package view;

import java.util.Arrays;

public enum SymbolConverter {
    GUTTER(0, Constants.GUTTER_SYMBOL),
    STRIKE(10, Constants.STRIKE_SYMBOL);

    private final int hitPins;
    private final String symbol;

    SymbolConverter(int hitPins, String symbol) {
        this.hitPins = hitPins;
        this.symbol = symbol;
    }

    public static String convert(final int hitPins) {
        return Arrays.stream(values())
                .filter(symbolConverter -> symbolConverter.hitPins == hitPins)
                .findFirst()
                .map(symbolConverter -> symbolConverter.symbol)
                .orElseGet(() -> String.valueOf(hitPins));
    }

    private static class Constants {
        public static final String STRIKE_SYMBOL = "X";
        public static final String GUTTER_SYMBOL = "-";
    }
}
