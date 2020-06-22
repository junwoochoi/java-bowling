package domain.state;

import domain.pin.Pins;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinishState implements State {
    public static final int MAX_TURN_COUNT = 2;
    protected Pins leftPins;
    protected List<Integer> fallenPinsHistory;

    public FinishState(final List<Integer> fallenPinsHistory) {
        validateFallenPinsHistory(fallenPinsHistory);
        this.fallenPinsHistory = Collections.unmodifiableList(new ArrayList<>(fallenPinsHistory));
        final int sumOfFallenPins = fallenPinsHistory.stream()
                .mapToInt(Integer::intValue)
                .sum();
        this.leftPins = Pins.ALL_STANDING_PINS.fellDown(sumOfFallenPins);
    }

    private void validateFallenPinsHistory(List<Integer> leftPinsHistory) {
        if (CollectionUtils.isEmpty(leftPinsHistory)) {
            throw new IllegalArgumentException("pinsHistory can not be empty");
        }
        if (leftPinsHistory.size() > MAX_TURN_COUNT) {
            throw new IllegalArgumentException("leftPinsHistory size can not be greater than " + MAX_TURN_COUNT);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State throwBall(int inputFallenPins) {
        throw new IllegalArgumentException("you can not throw more");
    }

    @Override
    public List<Integer> getFallenPinsHistory() {
        return Collections.unmodifiableList(new ArrayList<>(fallenPinsHistory));
    }

    @Override
    public Pins getLeftPins() {
        return this.getFallenPinsHistory().stream()
                .reduce(Pins.ALL_STANDING_PINS, Pins::fellDown, (pins, pins2) -> {
                    throw new IllegalArgumentException("multi thread not supported");
                });
    }


}
