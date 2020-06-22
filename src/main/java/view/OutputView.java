package view;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.player.Player;
import view.dto.BowlingPrintDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.player.Player.NAME_LENGTH;
import static java.util.stream.Collectors.toList;

public class OutputView {
    public static final String THREE_SPACE = "   ";
    public static final String NAME = "NAME";
    public static final String BLANK = " ";
    public static final String DELIMITER = "|";

    private final FrameHistoryParser frameHistoryParser = new FrameHistoryParser();

    public void printInputPlayerNameMessage() {
        System.out.println("플레이어 이름은(" + NAME_LENGTH + " english letters)?: ");
    }

    public void printInputFallenPinsMessageOf(int nextFrame) {
        System.out.println(nextFrame + " 프레임 투구 : ");
    }

    public void printBowling(BowlingPrintDto dto) {
        printHeader();
        printResult(dto.getPlayer(), dto.getFrames());
    }


    private void printHeader() {
        final String header = IntStream.rangeClosed(Frames.MIN_FRAME_COUNT, Frames.MAX_FRAME_COUNT)
                .mapToObj(Objects::toString)
                .map(s -> BLANK + BLANK + s + BLANK + BLANK)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(header);
    }

    private void printResult(Player player, List<Frame> frames) {
        final List<String> parsedFrames = frames.stream()
                .map(Frame::getFrameHistories)
                .map(frameHistoryParser::parse)
                .collect(toList());

        while (parsedFrames.size() != 10) {
            parsedFrames.add(THREE_SPACE);
        }

        final String result = IntStream.rangeClosed(Frames.MIN_FRAME_COUNT - 1, Frames.MAX_FRAME_COUNT - 1)
                .mapToObj(parsedFrames::get)
                .map(s -> BLANK + s + BLANK)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(result);
    }
}
