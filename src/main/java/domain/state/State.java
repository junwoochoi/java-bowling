package domain.state;

public interface State {

    boolean isFinished();

    State throwBall(int inputFallenPins);

}
