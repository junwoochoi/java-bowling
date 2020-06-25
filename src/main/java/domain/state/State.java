package domain.state;

import domain.pin.Pins;

public interface State {

    boolean isFinished();

    State throwBall(int inputFallenPins);

    Pins getFirstPins();

    Pins getSecondPins();

    Pins getLeftPins();


}
