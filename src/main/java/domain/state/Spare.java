package domain.state;

import domain.pin.Pins;

public class Spare extends FinishState {
    private final Pins pins;

    private Spare(final Pins leftPins) {
        this.pins = leftPins;
    }

    public static Spare newInstance(final Pins leftPins) {
        return new Spare(leftPins);
    }
}
