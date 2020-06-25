package domain.state;

import domain.pin.Pins;
import domain.state.finish.Miss;
import domain.state.finish.Spare;
import domain.state.playing.NormalState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalStateTest {

    @Test
    @DisplayName("NormalState 객체를 생성한다")
    void testNewInstance() {
        final State state = NormalState.byFirstPins(Pins.ALL_STANDING_PINS.fellDown(4));

        assertThat(state).isNotNull()
                .isOfAnyClassIn(NormalState.class);
    }

    @Test
    @DisplayName("Normal State는 아직 던질 기회가 남아있다")
    void testIsFinished() {
        final State state = NormalState.byFirstPins(Pins.ALL_STANDING_PINS.fellDown(1));

        assertThat(state.isFinished()).isFalse();
    }


    @Test
    @DisplayName("Normal State에서 남은 공을 다 쓰러트리면 Spare객체가 생성된다")
    void testNextSpare() {
        final State state = NormalState.byFirstPins(Pins.ALL_STANDING_PINS.fellDown(1));

        final State nextState = state.throwBall(9);

        assertThat(nextState)
                .isNotNull()
                .isOfAnyClassIn(Spare.class);
    }

    @Test
    @DisplayName("Normal State에서 남은 공을 다 쓰러트리지 못하면 Miss객체가 생성된다")
    void testNextMiss() {
        final State state = NormalState.byFirstPins(Pins.ALL_STANDING_PINS.fellDown(1));

        final State nextState = state.throwBall(1);

        assertThat(nextState)
                .isNotNull()
                .isOfAnyClassIn(Miss.class);
    }


}