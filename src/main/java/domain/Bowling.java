package domain;

import java.util.Objects;

public class Bowling {

    private final Player player;
    private final Frames frames;

    private Bowling(Player player) {
        Objects.requireNonNull(player);
        this.player = player;
        this.frames = Frames.empty();
    }

    public static Bowling newInstance(Player player) {
        return new Bowling(player);
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int nextFrame() {
        return frames.nextFrame();
    }

    public void throwBowlingBall(int inputFallenPins) {
        frames.throwBowlingBall(inputFallenPins);
    }
}
