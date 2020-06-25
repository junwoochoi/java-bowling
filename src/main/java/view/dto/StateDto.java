package view.dto;

import domain.state.State;
import view.StateFormatter;

import java.util.Objects;

public class StateDto {
    private final State state;

    public StateDto(State state) {
        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("state can not be null");
        }
        this.state = state;
    }


    public Class<? extends State> getStateClass() {
        return this.state.getClass();
    }

    public String toFormatString() {
        return StateFormatter.format(this);
    }

    public int getFirstHit() {
        return this.state.getFirstPins().fallenPins();
    }

    public int getSecondHit() {
        return this.state.getSecondPins().fallenPins();
    }
}
