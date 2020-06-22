package domain;

import domain.frame.Frame;
import domain.frame.impl.FinalFrame;
import domain.pin.Pins;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("스트라이크이면 한번 더 던질 수 있다.")
    void testStrikeOneMoreChance() {
        final FinalFrame finalFrame = FinalFrame.newInstance();

        finalFrame.throwBall(10);

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("스페어면 한번 더 던질 수 있다")
    void testSpareOneMoreChance() {
        final FinalFrame finalFrame = FinalFrame.newInstance();

        final int firstThrow = 3;
        finalFrame.throwBall(firstThrow);
        finalFrame.throwBall(Pins.MAX_NUMBER_OF_PINS - firstThrow);

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("처음 스트라이크를 던진 후, 두번째 턴에는 거터, 이후 마지막턴에 스페어 처리가 가능하다")
    void testFirstStrikeAndGutterAndSpare() {
        final FinalFrame finalFrame = FinalFrame.newInstance();
        final int secondThrow = 4;

        finalFrame.throwBall(Pins.MAX_NUMBER_OF_PINS);
        finalFrame.throwBall(secondThrow);

        Assertions.assertDoesNotThrow(() ->
                finalFrame.throwBall(Pins.MAX_NUMBER_OF_PINS - secondThrow)
        );


    }

    @Test
    @DisplayName("스페어나 스트라이크가 아니라면 세번 던질 수는 없다.")
    void testNoChanceIfNotSpareOrStrike() {
        final FinalFrame finalFrame = FinalFrame.newInstance();

        finalFrame.throwBall(3);
        finalFrame.throwBall(3);

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("공을 던지면 공을 던진 이력이 남는다")
    void testHistory() {
        final Frame finalFrame = FinalFrame.newInstance();

        finalFrame.throwBall(3);

        assertThat(finalFrame.getFrameHistories()).hasSize(1)
                .element(0).isEqualTo(3);
    }

}