package domain.state.finish;

import domain.pin.Pins;
import domain.state.State;

public class Spare extends FinishState {
    private final Pins firstHitPins;
    private final Pins secondHitPins;

    private Spare(final Pins firstHit) {
        this.firstHitPins = firstHit;
        this.secondHitPins = Pins.ALL_STANDING_PINS.fellDown(firstHitPins.leftPins());
    }

    public static State byFirstHit(Pins firstHit) {
        return new Spare(firstHit);
    }

    @Override
    public Pins getFirstPins() {
        return firstHitPins;
    }

    @Override
    public Pins getSecondPins() {
        return secondHitPins;
    }

    @Override
    public Pins getLeftPins() {
        return Pins.ALL_DOWN_PINS;
    }
}
