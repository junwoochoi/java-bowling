package domain.frame.impl;

import com.google.common.collect.Lists;
import domain.frame.Frame;
import domain.frame.Frames;
import domain.pin.Pins;
import domain.state.State;
import domain.state.playing.Ready;

import java.util.List;

public class FinalFrame extends Frame {

    private State bonusState;
    private boolean isBonusThrown = false;

    private FinalFrame(final State state) {
        super(state, Frames.MAX_FRAME_COUNT);
    }

    public static FinalFrame newInstance() {
        return new FinalFrame(Ready.newInstance());
    }

    @Override
    public boolean isFinished() {
        if (this.state.isFinished() && this.state.getLeftPins().isAllDown()) {
            return this.isBonusThrown;
        }

        return this.state.isFinished();
    }

    @Override
    public void throwBall(int inputFallenPins) {
        final Pins leftPinsInFirstState = this.state.getLeftPins();
        if (this.state.isFinished() && leftPinsInFirstState.isAllDown()) {
            this.bonusState = Ready.newInstance().throwBall(inputFallenPins);
            this.isBonusThrown = true;
            return;
        }

        super.throwBall(inputFallenPins);
    }

    @Override
    public List<State> getStates() {
        if (isBonusThrown) {
            return Lists.newArrayList(state, bonusState);
        }
        return super.getStates();
    }
}
