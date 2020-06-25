package domain.state.playing;

import domain.pin.Pins;
import domain.state.State;
import domain.state.finish.Miss;
import domain.state.finish.Spare;

import java.util.Objects;

public class NormalState implements State {

    private final Pins firstPins;

    private NormalState(final Pins firstHitPins) {
        if (Objects.isNull(firstHitPins) || firstHitPins.isAllDown()) {
            throw new IllegalArgumentException("invalid pins");
        }
        this.firstPins = firstHitPins;
    }

    public static State byFirstPins(Pins firstHitPins) {
        return new NormalState(firstHitPins);
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

        final Pins currentPins = this.firstPins;

        final Pins nextLeftPins = currentPins.fellDown(inputFallenPins);

        if (nextLeftPins.isAllDown()) {
            return Spare.byFirstHit(firstPins);
        }

        return Miss.by(firstPins, Pins.byNumberOfFallen(inputFallenPins));
    }

    @Override
    public Pins getFirstPins() {
        return this.firstPins;
    }

    @Override
    public Pins getSecondPins() {
        throw new IllegalArgumentException("no second pins exists");
    }


    @Override
    public Pins getLeftPins() {
        return this.firstPins;
    }
}
