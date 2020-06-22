package domain.state;

import domain.pin.Pins;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalState implements State {

    private final Pins leftPins;

    private NormalState(final Pins leftPins) {
        if (Objects.isNull(leftPins) || leftPins.isAllDown()) {
            throw new IllegalArgumentException("invalid pins");
        }
        this.leftPins = leftPins;
    }

    public static State byLeftPins(Pins leftPins) {
        return new NormalState(leftPins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State throwBall(int inputFallenPins) {
        if (isFinished()) {
            throw new IllegalArgumentException("can not throw more");
        }
        final Pins nextLeftPins = this.leftPins.fellDown(inputFallenPins);

        final int firstFallenPins = Pins.MAX_NUMBER_OF_PINS - leftPins.leftPins();
        final List<Integer> nextHistory = Arrays.asList(firstFallenPins, inputFallenPins);

        if (nextLeftPins.isAllDown()) {
            return Spare.of(nextHistory);
        }
        return Miss.of(nextHistory);
    }

    @Override
    public List<Integer> getFallenPinsHistory() {
        return Collections.unmodifiableList(Collections.singletonList(Pins.MAX_NUMBER_OF_PINS - leftPins.leftPins()));
    }

    @Override
    public Pins getLeftPins() {
        return this.getFallenPinsHistory().stream()
                .reduce(Pins.ALL_STANDING_PINS, Pins::fellDown, (pins, pins2) -> {
                    throw new IllegalArgumentException("multi thread not supported");
                });
    }
}
