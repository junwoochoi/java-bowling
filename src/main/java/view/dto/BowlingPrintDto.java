package view.dto;

import domain.bowling.Bowling;
import domain.frame.Frame;
import domain.player.Player;

import java.util.Collections;
import java.util.List;

public class BowlingPrintDto {

    private List<Frame> frames;
    private Player player;

    public BowlingPrintDto(Bowling bowling) {
        this.player = bowling.getPlayer();
        this.frames = bowling.getFrames().getFrames();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public Player getPlayer() {
        return player;
    }

    public int nextFrameCount() {
        return frames.stream()
                .filter(Frame::isThrowOpportunityLeft)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이미 종료된 게임 입니다."))
                .getFrameNumber();
    }
}
