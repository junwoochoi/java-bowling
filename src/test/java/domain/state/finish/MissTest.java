package domain.state.finish;

import domain.pin.Pins;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MissTest {

    @ParameterizedTest
    @CsvSource({"3,4", "1,3"})
    @DisplayName("첫번째 넘어트린 핀과 두번째 넘어뜨린 핀의 인자를 받아 Miss객체를 생성한다")
    void testCreate(int firstPins, int secondPins) {
        final State miss = Miss.by(Pins.byNumberOfFallen(firstPins), Pins.byNumberOfFallen(secondPins));

        assertThat(miss).isNotNull();
    }


    @ParameterizedTest
    @CsvSource({"10, 0", "3,7"})
    @DisplayName("첫번째 넘어트린 핀과 두번째 넘어뜨린 핀의 합이 모두 쓰러진 핀이면 Miss객체를 생성 실패한다")
    void testValidation(int firstPins, int secondPins) {
        assertThrows(IllegalArgumentException.class, () ->
                Miss.by(Pins.byNumberOfFallen(firstPins), Pins.byNumberOfFallen(secondPins)));
    }


}