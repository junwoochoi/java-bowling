package domain.state;

import domain.pin.Pins;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinishState implements State {
    public static final int MAX_TURN_COUNT = 2;
    protected Pins leftPins;
    protected List<Pins> leftPinsHistory;

    public FinishState(final List<Pins> leftPinsHistory) {
        validateLeftPinsHistory(leftPinsHistory);
        this.leftPins = leftPinsHistory.get(leftPinsHistory.size() - 1);
        this.leftPinsHistory = Collections.unmodifiableList(new ArrayList<>(leftPinsHistory));
    }

    private void validateLeftPinsHistory(List<Pins> leftPinsHistory) {
        if (CollectionUtils.isEmpty(leftPinsHistory)) {
            throw new IllegalArgumentException("pinsHistory can not be empty");
        }
        if (leftPinsHistory.size() > MAX_TURN_COUNT) {
            throw new IllegalArgumentException("leftPinsHistory size can not be greater than " + MAX_TURN_COUNT);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State throwBall(int inputFallenPins) {
        throw new IllegalArgumentException("you can not throw more");
    }

    @Override
    public List<Pins> getLeftPinsHistory() {
        return Collections.unmodifiableList(new ArrayList<>(leftPinsHistory));
    }


}
