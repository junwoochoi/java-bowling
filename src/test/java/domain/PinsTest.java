package domain;

import domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PinsTest {

    @Test
    @DisplayName("Pins 객체 생성 테스트")
    void test() {
        final Pins pins = Pins.ALL_STANDING_PINS;

        assertThat(pins).isNotNull();
    }

    @Test
    @DisplayName("ALL_STANDING_PINS은 핀이 모두 서있는 Pins를 리턴한다.")
    void testAllStandingPins() {
        final Pins pins = Pins.ALL_STANDING_PINS;

        assertThat(pins.isAllDown()).isFalse();
    }

    @Test
    @DisplayName("ALL_DOWN_PINS은 핀이 모두 넘어져있는 Pins를 리턴한다.")
    void testAllDownPins() {
        final Pins pins = Pins.ALL_DOWN_PINS;

        assertThat(pins.isAllDown()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("넘어질 핀의 값을 주면 해당 값만큼의 핀이 줄어든 Pins객체를 새롭게 리턴한다")
    @ValueSource(ints = {0, 5, 10})
    void testThrowBallAndLeftPins(int fallenPins) {
        final Pins pins = Pins.ALL_STANDING_PINS;

        final Pins fellDown = pins.fellDown(fallenPins);

        assertThat(fellDown.leftPins()).isEqualTo(Pins.MAX_NUMBER_OF_PINS - fallenPins);
    }

    @ParameterizedTest
    @DisplayName("넘어질 핀의 갯수는 음수이거나 NUMBER_OF_PINS를 넘어갈 수 없다")
    @ValueSource(ints = {-1, 15})
    void testValidationOfFallenPins(int fallenPins) {
        final Pins pins = Pins.ALL_STANDING_PINS;

        assertThrows(IllegalArgumentException.class, () ->
                pins.fellDown(fallenPins));
    }

    @Test
    @DisplayName("넘어질 핀이 남은 핀의 숫자를 넘어갈 수 없다")
    void testValiationOfFallenPinsGreaterThanLeftPins() {
        final Pins pins = Pins.ALL_STANDING_PINS;

        pins.fellDown(6);

        assertThrows(IllegalArgumentException.class, () ->
                pins.fellDown(pins.leftPins() + 1));
    }

    @Test
    @DisplayName("처음 객체 생성시 남은 핀의 숫자는 Pins.NUMBER_OF_PINS 이다")
    void testLeftPins() {
        final Pins pins = Pins.ALL_STANDING_PINS;

        assertThat(pins.leftPins()).isEqualTo(Pins.MAX_NUMBER_OF_PINS);
    }
}