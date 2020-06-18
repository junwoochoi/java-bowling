package domain.state;

import domain.pin.Pins;

import java.util.List;

public class Spare extends FinishState {
    private Spare(final List<Pins> pinsHistory) {
        super(pinsHistory);
    }

    public static State of(List<Pins> pinsHistory) {
        return new Spare(pinsHistory);
    }
}
