package domain.pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Pins {
    public static final int MAX_NUMBER_OF_PINS = 10;
    public static final int ZERO = 0;
    public static final Pins ALL_DOWN_PINS = new Pins(Stream.generate(Pin::newFallenPin)
            .limit(MAX_NUMBER_OF_PINS)
            .collect(collectingAndThen(toList(), Collections::unmodifiableList)));
    public static final Pins ALL_STANDING_PINS = new Pins();

    public static Pins byNumberOfStanding(int standingCount) {
        return new Pins(standingCount);
    }
    private final List<Pin> pins;

    private Pins() {
        this.pins = Stream.generate(Pin::newStandingPin)
                .limit(MAX_NUMBER_OF_PINS)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }


    private Pins(List<Pin> pins) {
        this.pins = Collections.unmodifiableList(new ArrayList<>(pins));
    }

    private Pins(int standingCount) {
        validateFallenPins(standingCount);

        final List<Pin> nextPins = Stream.concat(
                Stream.generate(Pin::newStandingPin)
                        .limit(MAX_NUMBER_OF_PINS - standingCount),
                Stream.generate(Pin::newFallenPin)
                        .limit(standingCount)
        )
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));

        this.pins = Collections.unmodifiableList(new ArrayList<>(nextPins));
    }

    public Pins fellDown(int fallenPins) {
        validateFallenPins(fallenPins);

        final List<Pin> nextPins = Stream.concat(
                Stream.generate(Pin::newStandingPin)
                        .limit(leftPins() - fallenPins),
                Stream.generate(Pin::newFallenPin)
                        .limit(fallenPins)
        )
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));

        return new Pins(nextPins);
    }

    private void validateFallenPins(int fallenPins) {
        if (fallenPins < ZERO) {
            throw new IllegalArgumentException("fallen pins min value is [" + ZERO + "]");
        }
        if (fallenPins > MAX_NUMBER_OF_PINS) {
            throw new IllegalArgumentException("fallen pins max value is [" + MAX_NUMBER_OF_PINS + "]");
        }
        if (leftPins() - fallenPins < ZERO) {
            throw new IllegalArgumentException("left pins must be positive or zero");
        }
    }

    public int leftPins() {
        return (int) this.pins.stream()
                .filter(Pin::isStanding)
                .count();
    }

    public boolean isAllDown() {
        return leftPins() == ZERO;
    }
}
