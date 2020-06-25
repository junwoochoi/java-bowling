package domain.state.playing;

import domain.pin.Pins;
import domain.state.State;
import domain.state.finish.Strike;

public class Ready implements State {

    private final Pins pins = Pins.ALL_STANDING_PINS;

    private Ready() {

    }

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


        if (leftPins.isAllDown()) {
            return Strike.getInstance();
        }

        return NormalState.byFirstPins(leftPins);
    }

    @Override
    public Pins getFirstPins() {
        throw new IllegalArgumentException("not valid request");
    }

    @Override
    public Pins getSecondPins() {
        throw new IllegalArgumentException("not valid request");
    }


    @Override
    public Pins getLeftPins() {
        return Pins.ALL_STANDING_PINS;
    }


}
