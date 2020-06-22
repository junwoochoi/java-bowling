package domain.state;

import java.util.List;

public class Miss extends FinishState {

    public Miss(List<Integer> fallenPinsHistory) {
        super(fallenPinsHistory);
    }

    public static State of(List<Integer> nextHistory) {
        return new Miss(nextHistory);
    }
}
