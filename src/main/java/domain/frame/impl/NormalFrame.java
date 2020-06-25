package domain.frame.impl;

import domain.frame.Frame;
import domain.state.State;
import domain.state.playing.Ready;

public class NormalFrame extends Frame {

    private NormalFrame(final State state, final int frameNumber) {
        super(state, frameNumber);
    }

    public static NormalFrame newInstanceByFrameNumber(final int frameNumber) {
        return new NormalFrame(Ready.newInstance(), frameNumber);
    }

    @Override
    public boolean isFinished() {
        return this.state.isFinished();
    }
}
