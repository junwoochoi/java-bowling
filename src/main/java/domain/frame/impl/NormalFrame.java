package domain.frame.impl;

import domain.frame.Frame;
import domain.state.Ready;
import domain.state.State;

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
