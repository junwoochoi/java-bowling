package domain.frame;

import domain.pin.Pins;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    protected Pins pins;
    protected List<Integer> frameHistories = new ArrayList<>();
    protected int frameNumber;

    public Frame() {
        this.pins = Pins.newInstance();
    }

    public abstract boolean isDone();

    public boolean isThrowOpportunityLeft() {
        return !this.isDone();
    }

    public abstract void throwBowlingBall(int fallenPins);

    public List<Integer> getFrameHistories() {
        return frameHistories;
    }

    public int getFrameNumber() {
        return frameNumber;
    }
}
