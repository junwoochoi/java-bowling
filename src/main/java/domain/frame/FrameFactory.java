package domain.frame;


import domain.frame.impl.FinalFrame;
import domain.frame.impl.NormalFrame;

public class FrameFactory {
    private FrameFactory() {

    }

    public static Frame createByFrameNumber(int nextFrameNumber) {
        if (nextFrameNumber == Frames.MAX_FRAME_COUNT) {
            return FinalFrame.newInstance();
        }

        return NormalFrame.newInstanceByFrameNumber(nextFrameNumber);
    }
}
