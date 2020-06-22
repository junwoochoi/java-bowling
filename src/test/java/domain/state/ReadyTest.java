package domain.state;

import domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @DisplayName("Ready 객체를 생성한다")
    @Test
    void testNewInstance() {
        final Ready ready = Ready.newInstance();

        assertThat(ready).isNotNull();
    }

    @DisplayName("Ready 객체에서 기록을 리턴하면 비어있는 리스트를 리턴한다")
    @Test
    void testHistories() {
        final Ready ready = Ready.newInstance();

        assertThat(ready.getFallenPinsHistory())
                .isNotNull()
                .isNullOrEmpty();
    }

    @DisplayName("Ready 객체에서 공을 던져 다 넘어뜨리면 Strike 객체를 리턴한다")
    @Test
    void testStrike() {
        final Ready ready = Ready.newInstance();

        final State nextState = ready.throwBall(Pins.MAX_NUMBER_OF_PINS);

        assertThat(nextState).isNotNull()
                .isOfAnyClassIn(Strike.class);
    }

    @DisplayName("Ready 객체에서 공을 던져 다 넘어뜨리지 못하면 NormalState 객체를 리턴한다")
    @Test
    void testNormalState() {
        final Ready ready = Ready.newInstance();

        final State nextState = ready.throwBall(3);

        assertThat(nextState).isNotNull()
                .isOfAnyClassIn(NormalState.class);
    }
}