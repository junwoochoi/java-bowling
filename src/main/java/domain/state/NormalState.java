package domain.state;

import domain.pin.Pins;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalState implements State {

    private final Pins leftPins;

    public NormalState(final Pins leftPins) {
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
        final Pins nextLeftPins = this.leftPins.fellDown(inputFallenPins);

        final List<Pins> nextHistory = Arrays.asList(this.leftPins, nextLeftPins);

        if (nextLeftPins.isAllDown()) {
            return Spare.of(nextHistory);
        }
        return Miss.of(nextHistory);
    }

    @Override
    public List<Pins> getLeftPinsHistory() {
        return Collections.unmodifiableList(Collections.singletonList(leftPins));
    }
}
