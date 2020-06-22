package domain.state;

import domain.pin.Pins;

import java.util.Arrays;
import java.util.Collections;

public class Strike extends FinishState {

    private Strike() {
        super(Collections.unmodifiableList(Arrays.asList(Pins.MAX_NUMBER_OF_PINS)));
    }

    public static Strike newInstance() {
        return new Strike();
    }

}
