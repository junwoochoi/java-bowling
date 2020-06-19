package domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @DisplayName("Strike 객체를 생성한다")
    @Test
    void testNewInstance() {
        final Strike strike = Strike.newInstance();

        assertThat(strike).isNotNull();
    }

    @DisplayName("Strike는 더이상 던질 수 없다.")
    @Test
    void testAllPinsDown() {
        final Strike strike = Strike.newInstance();

        assertThat(strike.isFinished()).isTrue();
    }


}