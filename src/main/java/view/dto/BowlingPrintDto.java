package view.dto;

import domain.Bowling;
import domain.Frame;
import domain.Player;

import java.util.List;

public class BowlingPrintDto {

    private List<Frame> frames;
    private Player player;

    public BowlingPrintDto(Bowling bowling) {
        this.player = bowling.getPlayer();
        this.frames = bowling.getFrames().getFrames();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Player getPlayer() {
        return player;
    }

    public int nextFrameCount() {
        return (int) frames.stream()
                .filter(Frame::isDone)
                .count() + 1;
    }
}
