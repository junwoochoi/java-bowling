package domain.state.finish;

import domain.pin.Pins;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpareTest {

    @Test
    @DisplayName("처음 던져서 쓰러트린 핀으로 Spare 객체를 생성한다.")
    void testCreate() {
        final State state = Spare.byFirstHit(Pins.byNumberOfFallen(1));

        assertThat(state).isNotNull();
    }

    @Test
    @DisplayName("Spare는 모든 핀이 쓰러진 상태이다")
    void testSpareAllDown() {
        final State spare = Spare.byFirstHit(Pins.byNumberOfFallen(1));

        assertThat(spare.isFinished()).isTrue();

    }

    @DisplayName("Spare는 더이상 던질 수 없다.")
    @Test
    void testCannotThrowMore() {
        final State spare = Spare.byFirstHit(Pins.byNumberOfFallen(1));

        assertThrows(IllegalArgumentException.class, () ->
                spare.throwBall(3));
    }

}