package domain.frame.impl;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.state.Ready;
import domain.state.State;

public class FinalFrame extends Frame {

    private FinalFrame(final State state) {
        super(state, Frames.MAX_FRAME_COUNT);
    }

    public static FinalFrame newInstance() {
        return new FinalFrame(Ready.newInstance());
    }

    @Override
    public boolean isFinished() {
        return this.state.isFinished();
    }

}
