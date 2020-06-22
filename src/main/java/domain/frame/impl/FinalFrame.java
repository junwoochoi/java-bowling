package domain.frame.impl;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.pin.Pins;
import domain.state.Ready;
import domain.state.State;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinalFrame extends Frame {

    private State bonusState = Ready.newInstance();
    private boolean isBonusThrown = false;

    private FinalFrame(final State state) {
        super(state, Frames.MAX_FRAME_COUNT);
    }

    public static FinalFrame newInstance() {
        return new FinalFrame(Ready.newInstance());
    }

    @Override
    public boolean isFinished() {
        this.state.getLeftPins();
        if (this.state.isFinished() && this.state.getLeftPins().isAllDown()) {
            return this.isBonusThrown;
        }

        return this.state.isFinished();
    }

    @Override
    public void throwBall(int inputFallenPins) {
        final Pins leftPinsInFirstState = this.state.getLeftPins();
        if (this.state.isFinished() && leftPinsInFirstState.isAllDown()) {
            this.bonusState.throwBall(inputFallenPins);
            this.isBonusThrown = true;
            return;
        }

        super.throwBall(inputFallenPins);
    }

    @Override
    public List<Integer> getFrameHistories() {
        if (this.isBonusThrown) {
            return Collections.unmodifiableList(
                    Stream.of(this.state.getFallenPinsHistory(), this.bonusState.getFallenPinsHistory())
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList())
            );
        }
        return super.getFrameHistories();
    }
}
