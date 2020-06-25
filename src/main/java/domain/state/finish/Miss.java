package domain.state.finish;

import domain.pin.Pins;
import domain.state.State;

public class Miss extends FinishState {

    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(final Pins firstHitPins, final Pins secondHitPins) {
        validatePins(firstHitPins, secondHitPins);
        this.firstPins = firstHitPins;
        this.secondPins = secondHitPins;
    }

    private void validatePins(Pins firstHitPins, Pins secondHitPins) {
        if (firstHitPins.isAllDown() || secondHitPins.isAllDown() || firstHitPins.fellDown(secondHitPins.fallenPins()).isAllDown()) {
            throw new IllegalArgumentException("it is not MISS");
        }
    }

    public static State by(final Pins firstHitPins, final Pins secondHitPins) {
        return new Miss(firstHitPins, secondHitPins);
    }


    @Override
    public Pins getFirstPins() {
        return this.firstPins;
    }

    @Override
    public Pins getSecondPins() {
        return this.secondPins;
    }

    @Override
    public Pins getLeftPins() {
        return Pins.ALL_STANDING_PINS.fellDown(this.firstPins.fallenPins() + this.secondPins.fallenPins());
    }
}
