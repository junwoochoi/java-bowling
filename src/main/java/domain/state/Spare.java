package domain.state;

import java.util.List;

public class Spare extends FinishState {
    private Spare(final List<Integer> pinsHistory) {
        super(pinsHistory);
    }

    public static State of(List<Integer> pinsHistory) {
        return new Spare(pinsHistory);
    }
}
