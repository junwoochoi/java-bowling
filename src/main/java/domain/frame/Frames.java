package domain.frame;


import domain.frame.impl.FinalFrame;
import domain.frame.impl.NormalFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int MAX_FRAME_COUNT = 10;
    public static final int MIN_FRAME_COUNT = 1;
    private final List<Frame> frames;

    private Frames() {
        final List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.newInstanceByFrameNumber(MIN_FRAME_COUNT));
        this.frames = frames;
    }

    public static Frames newInstance() {
        return new Frames();
    }

    public boolean isEnd() {
        return (this.frames.size() == MAX_FRAME_COUNT) && this.frames.get(MAX_FRAME_COUNT - 1).isFinished();
    }

    public void throwBowlingBall(int inputFallenPins) {
        if (isEnd()) {
            throw new IllegalArgumentException("더이상 던질 기회가 없습니다.");
        }

        final Frame lastThrownFrame = frames.get(frames.size() - 1);
        if (lastThrownFrame.isThrowOpportunityLeft()) {
            lastThrownFrame.throwBall(inputFallenPins);
            return;
        }

        final int nextFrameNumber = lastThrownFrame.getFrameNumber() + 1;

        final Frame nextFrame = FrameFactory.createByFrameNumber(nextFrameNumber);
        
        nextFrame.throwBall(inputFallenPins);
        frames.add(nextFrame);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(new ArrayList<>(this.frames));
    }

}
