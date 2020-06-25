package domain.state.finish;

import domain.pin.Pins;

public class Strike extends FinishState {

    private static final Strike singletonInstance = new Strike();
    private final Pins firstPins;

    private Strike() {
        this.firstPins = Pins.ALL_DOWN_PINS;
    }

    public static Strike getInstance() {
        return singletonInstance;
    }

    @Override
    public Pins getFirstPins() {
        return firstPins;
    }

    @Override
    public Pins getSecondPins() {
        throw new IllegalArgumentException("not valid request");
    }

    @Override
    public Pins getLeftPins() {
        return Pins.ALL_DOWN_PINS;
    }
}
