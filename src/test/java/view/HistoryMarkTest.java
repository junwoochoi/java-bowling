package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class HistoryMarkTest {

    @DisplayName("숫자에 해당하는 적절한 마크를 리턴하고, 마크가 존재하지 않으면 해당 숫자를 String으로 리턴한다")
    @ParameterizedTest
    @CsvSource({"1,1", "5,5", "10,X", "0,-"})
    void testFindMark(int numberOfPins, String expectedMark) {
        final String mark = HistoryMark.findMarkOrItSelf(numberOfPins, frameHistories.size());

        assertThat(mark).isEqualTo(expectedMark);
    }
}