package domain;

import domain.frame.impl.NormalFrame;
import domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalFrameTest {

    @Test
    @DisplayName("Frame 객체를 생성한다")
    void testnewInstanceByFrameNumber() {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        assertThat(normalFrame).isNotNull();
    }

    @Test
    @DisplayName("Frame에서 공을 던지면 넘어진 핀의 기록이 남는다")
    void testThrowBall() {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        normalFrame.throwBall(4);

        assertThat(normalFrame.getState())
                .hasSize(1)
                .element(0).isEqualTo(4);
    }

    @ParameterizedTest
    @CsvSource({"1,false", "2,true"})
    @DisplayName("isDone 메소드로 공이 모두 던져졌는지 확인할 수 있다")
    void testIsDone(int throwCount, boolean isDone) {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        for (int i = 0; i < throwCount; i++) {
            normalFrame.throwBall(3);
        }

        assertThat(normalFrame.isFinished())
                .isEqualTo(isDone);
    }

    @Test
    @DisplayName("지정된 2 이상회으로 공을 던질 수 없다")
    void testValidationOverThrow() {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        for (int i = 0; i < 2; i++) {
            normalFrame.throwBall(2);
        }

        assertThrows(IllegalArgumentException.class, () ->
                normalFrame.throwBall(2));
    }

    @Test
    @DisplayName("첫 투구가 스트라이크일 때, 해당 턴은 끝난 것으로 인식된다")
    void testFirstStrikeEndTurn() {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        normalFrame.throwBall(Pins.MAX_NUMBER_OF_PINS);

        final boolean isDone = normalFrame.isFinished();

        assertThat(isDone).isTrue();
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 20})
    @DisplayName("공을 던졌을 때, 핀의 갯수를 초과하거나 음수이면 Exception을 던진다")
    void testThrowBallInvalidPinCount(int fallenPins) {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        assertThrows(IllegalArgumentException.class, () ->
                normalFrame.throwBall(fallenPins)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 10})
    @DisplayName("공을 던졌을 때, 넘어진 핀의 갯수에 대한 기록을 추가한다")
    void testThrowBallSuccess(int fallenPins) {
        final NormalFrame normalFrame = NormalFrame.newInstanceByFrameNumber(1);

        normalFrame.throwBall(fallenPins);


        final List<Integer> frameHistories = normalFrame.getState();
        final Integer lastHistory = frameHistories.get(frameHistories.size() - 1);

        assertThat(lastHistory).isEqualTo(fallenPins);
    }
}