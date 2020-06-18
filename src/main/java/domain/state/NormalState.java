package domain.state;

import domain.pin.Pins;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class NormalState implements State {

    protected final List<Pins> fallenPinsHistory;
    private final Pins leftPins;

    public NormalState(final Pins leftPins) {
        if (Objects.isNull(leftPins) || leftPins.isAllDown()) {
            throw new IllegalArgumentException("invalid pins");
        }
        this.fallenPinsHistory = Collections.unmodifiableList(
                Collections.singletonList(leftPins)
        );
        this.leftPins = leftPins;
    }

    public static State byLeftPins(Pins leftPins) {
        return new NormalState(leftPins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State throwBall(int inputFallenPins) {
        final Pins nextLeftPins = this.leftPins.fellDown(inputFallenPins);

        final List<Pins> nextHistory = Stream.concat(fallenPinsHistory.stream(), Stream.of(nextLeftPins))
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));

        if (nextLeftPins.isAllDown()) {
            return Spare.of(nextHistory);
        }
        return Miss.of(nextHistory);
    }
}
