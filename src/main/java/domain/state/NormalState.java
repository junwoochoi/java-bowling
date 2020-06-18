package domain.state;

import domain.pin.Pins;

import java.util.Objects;

public class NormalState implements State {

    private final Pins pins;

    public NormalState(final Pins leftPins) {
        if (Objects.isNull(leftPins) || leftPins.isAllDown()) {
            throw new IllegalArgumentException("invalid pins");
        }
        this.pins = leftPins;
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
        final Pins nextPins = this.pins.fellDown(inputFallenPins);

        if (nextPins.isAllDown()) {
            return Spare.newInstance(nextPins);
        }
        return Miss.newInstance();
    }
}
