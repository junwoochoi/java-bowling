package domain;

import domain.frame.Frames;
import domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @Test
    @DisplayName("Frames 객체 생성 테스트")
    void testConstructor() {
        assertDoesNotThrow(Frames::newInstance);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 9})
    @DisplayName("Frames는 모든 프레임이 종료되지 않으면 isEnd 가 false를 리턴한다")
    void testIsEndFalse(int numberOfThrows) {
        final Frames frames = Frames.newInstance();

        for (int i = 0; i < numberOfThrows; i++) {
            frames.throwBowlingBall(Pins.MAX_NUMBER_OF_PINS);
        }

        assertThat(frames.isEnd()).isFalse();
    }

    @Test
    @DisplayName("Frames는 모든 프레임이 종료되면 isEnd 가 true를 리턴한다")
    void testIsEndTrue() {
        final Frames frames = Frames.newInstance();

        // 마지막 프레임은 XX 가 가능하므로 모든 프레임을 완료시키기 위해 +1 해준다.
        for (int i = 0; i < Frames.MAX_FRAME_COUNT + 1; i++) {
            frames.throwBowlingBall(Pins.MAX_NUMBER_OF_PINS);
        }

        assertThat(frames.isEnd()).isTrue();
    }
}