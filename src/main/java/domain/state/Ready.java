package domain.state;

import domain.pin.Pins;

public class Ready implements State {

    private final Pins pins = Pins.newInstance();

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
            return Strike.newInstance();
        }

        return NormalState.byLeftPins(leftPins);
    }



}
