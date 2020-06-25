package domain.state;

import domain.state.finish.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrikeTest {

    @DisplayName("Strike 객체를 생성한다")
    @Test
    void testNewInstance() {
        final Strike strike = Strike.getInstance();

        assertThat(strike).isNotNull();
    }

    @DisplayName("Strike는 모든 핀이 쓰러진 상태이다.")
    @Test
    void testAllPinsDown() {
        final Strike strike = Strike.getInstance();

        assertThat(strike.isFinished()).isTrue();
    }

    @DisplayName("Strike는 더이상 던질 수 없다.")
    @Test
    void testCannotThrowMore() {
        final Strike strike = Strike.getInstance();

        assertThrows(IllegalArgumentException.class, () ->
                strike.throwBall(3));
    }


}