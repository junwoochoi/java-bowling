package domain.state;

import domain.pin.Pins;

import java.util.List;

public class Miss extends FinishState{

    public Miss(List<Pins> pinsHistory) {
        super(pinsHistory);
    }

    public static State of(List<Pins> nextHistory) {
        return new Miss(nextHistory);
    }
}
