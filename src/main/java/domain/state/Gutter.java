package domain.state;

import domain.pin.Pins;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.state.FinishState.MAX_TURN_COUNT;

public class Gutter implements State {

    private List<Integer> fallenHistory;

    private Gutter() {
        this.fallenHistory = Collections.singletonList(0);
    }

    public Gutter(int before) {
        this.fallenHistory = Arrays.asList(before, 0);
    }

    public static Gutter ofFirst() {
        return new Gutter();
    }

    public static Gutter ofSecond(int before) {
        return new Gutter(before);
    }

    @Override
    public boolean isFinished() {
        return fallenHistory.size() == MAX_TURN_COUNT;
    }

    @Override
    public State throwBall(int inputFallenPins) {
        if (fallenHistory.size() == MAX_TURN_COUNT) {
            throw new IllegalArgumentException("can not throw more");
        }
        final List<Integer> nextHistory = Arrays.asList(0, inputFallenPins);

        if (inputFallenPins == Pins.MAX_NUMBER_OF_PINS) {
            return Spare.of(nextHistory);
        }

        return Miss.of(nextHistory);
    }

    @Override
    public List<Integer> getFallenPinsHistory() {
        return this.fallenHistory;
    }

    @Override
    public Pins getLeftPins() {
        return Pins.ALL_STANDING_PINS.fellDown(this.fallenHistory.stream()
                .mapToInt(Integer::intValue)
                .sum());
    }
}
