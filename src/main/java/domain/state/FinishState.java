package domain.state;

import domain.pin.Pins;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinishState implements State {
    protected final List<Integer> hitHistories;
    private Pins leftPins;

    public FinishState(final List<Pins> fallPinsHistory) {
        if (CollectionUtils.isEmpty(fallPinsHistory)) {
            throw new IllegalArgumentException("pinsHistory can not be empty");
        }
        this.fallPinsHistory = new ArrayList<>(fallPinsHistory);
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
    public List<Integer> getHitHistories() {
        return this.hitHistories;
    }

}
