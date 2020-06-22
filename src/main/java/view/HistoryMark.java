package view;

import java.util.Arrays;
import java.util.function.Predicate;

public enum HistoryMark {

    STRIKE(10, (num) -> num != 2, "X"),
    SPARE(10, (num) -> num == 2, "/"),
    GUTTER(0, (num) -> true, "-");

    private final int numberOfPins;
    private final Predicate<Integer> tryCountMatcher;
    private final String mark;

    HistoryMark(final int numberOfPins, Predicate<Integer> tryCountMatcher, final String mark) {
        this.numberOfPins = numberOfPins;
        this.tryCountMatcher = tryCountMatcher;
        this.mark = mark;
    }

    public static String findMarkOrItSelf(int numberOfPins, int tryCount) {
        return Arrays.stream(values())
                .filter(historyMark -> historyMark.numberOfPins == numberOfPins)
                .filter(historyMark -> historyMark.tryCountMatcher.test(tryCount))
                .findAny()
                .map(historyMark -> historyMark.mark)
                .orElseGet(() -> String.valueOf(numberOfPins));
    }
}
