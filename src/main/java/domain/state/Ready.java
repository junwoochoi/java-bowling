package domain.state;

import domain.pin.Pins;

import java.util.Collections;
import java.util.List;

public class Ready implements State {

    private final Pins pins = Pins.ALL_STANDING_PINS;

    public static Ready newInstance() {
        return new Ready();
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
        Pins leftPins = this.pins.fellDown(inputFallenPins);

        if (inputFallenPins == 0) {
            return Gutter.ofFirst();
        }

        if (leftPins.isAllDown()) {
            return Strike.newInstance();
        }

        return NormalState.byLeftPins(leftPins);
    }

    @Override
    public List<Integer> getFallenPinsHistory() {
        return Collections.emptyList();
    }

    @Override
    public Pins getLeftPins() {
        return Pins.ALL_STANDING_PINS;
    }


}
