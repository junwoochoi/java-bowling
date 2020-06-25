package domain.state.finish;

import domain.state.State;

public abstract class FinishState implements State {
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State throwBall(int inputFallenPins) {
        throw new IllegalArgumentException("you can not throw more");
    }

}
