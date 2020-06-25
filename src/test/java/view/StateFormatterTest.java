package view;

import domain.pin.Pins;
import domain.state.finish.Miss;
import domain.state.finish.Spare;
import domain.state.finish.Strike;
import domain.state.playing.NormalState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import view.dto.StateDto;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StateFormatterTest {

    @DisplayName("각 State에 해당하는 적절한 포맷을 리턴한다")
    @ParameterizedTest
    @MethodSource("stateFormatProvider")
    void testFindMark(StateDto stateDto, String expectedFormat) {
        final String mark = StateFormatter.format(stateDto);

        assertThat(mark).isEqualTo(expectedFormat);
    }

    private static Stream<Arguments> stateFormatProvider() {
        return Stream.of(
                Arguments.of(new StateDto(Strike.getInstance()), "X"),
                Arguments.of(new StateDto(Spare.byFirstHit(Pins.byNumberOfFallen(3))), "3|/"),
                Arguments.of(new StateDto(NormalState.byFirstPins(Pins.byNumberOfFallen(1))), "1"),
                Arguments.of(new StateDto(Miss.by(Pins.byNumberOfFallen(3), Pins.byNumberOfFallen(4))), "3|4")
        );
    }
}