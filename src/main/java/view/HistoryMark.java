package view;

import domain.state.Gutter;
import domain.state.Spare;
import domain.state.State;
import domain.state.Strike;

import java.util.Arrays;
import java.util.function.Predicate;

public enum HistoryMark {

    STRIKE(10, Strike.class, "X"),
    SPARE(10, Spare.class, "/"),
    GUTTER(0, Gutter.class, "-");

    private final int numberOfPins;
    private final Class<? extends State> stateType;
    private final String mark;

    HistoryMark(final int numberOfPins, final Class<? extends State> stateType, final String mark) {
        this.numberOfPins = numberOfPins;
        this.stateType = stateType;
        this.mark = mark;
    }

    public static String findMarkOrItSelf(int numberOfPins, Class<? extends State> stateType) {
        return Arrays.stream(values())
                .filter(historyMark -> historyMark.numberOfPins == numberOfPins)
                .filter(historyMark -> historyMark.stateType.equals(stateType))
                .findAny()
                .map(historyMark -> historyMark.mark)
                .orElseGet(() -> String.valueOf(numberOfPins));
    }
}
