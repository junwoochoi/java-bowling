package domain.state;

import domain.pin.Pins;

import java.util.Collections;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Strike extends FinishState {

    private Strike() {
        super(Stream.of(Pins.ALL_DOWN_PINS)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList)));
    }

    public static Strike newInstance() {
        return new Strike();
    }

}
