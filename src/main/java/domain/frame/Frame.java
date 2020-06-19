package domain.frame;

import domain.pin.Pins;
import domain.state.State;
import spark.utils.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.pin.Pins.ZERO;
import static java.util.stream.Collectors.collectingAndThen;

public abstract class Frame {
    protected int frameNumber;
    protected State state;

    protected Frame(final State state, final int frameNumber) {
        Assert.notNull(state, "state can not be null");
        if (frameNumber <= ZERO || frameNumber > Frames.MAX_FRAME_COUNT) {
            throw new IllegalArgumentException("생성된 프레임의 숫자가 유효하지 않습니다.");
        }
        this.state = state;
        this.frameNumber = frameNumber;
    }

    public abstract boolean isFinished();

    public boolean isThrowOpportunityLeft() {
        return !this.isFinished();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void throwBall(int inputFallenPins) {
        this.state = this.state.throwBall(inputFallenPins);
    }

    public List<Integer> getFrameHistories() {
        return this.state.getLeftPinsHistory().stream()
                .map(Pins::leftPins)
                .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
