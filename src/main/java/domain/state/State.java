package domain.state;

import domain.pin.Pins;

import java.util.List;

public interface State {

    boolean isFinished();

    State throwBall(int inputFallenPins);

    List<Integer> getHitHistories();
}
