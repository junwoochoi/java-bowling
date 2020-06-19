package domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {

    @Test
    @DisplayName("Pin의 newStandingPin은 isStanding을 true로 리턴한다.")
    void newStandingPinTest() {
        Pin pin = Pin.newStandingPin();

        assertThat(pin).isNotNull();
        assertThat(pin.isStanding()).isTrue();
    }
    @Test
    @DisplayName("Pin의 newFallenPin은 isStanding을 false로 리턴한다.")
    void newFallenPinTest() {
        Pin pin = Pin.newFallenPin();

        assertThat(pin).isNotNull();
        assertThat(pin.isStanding()).isFalse();
    }



}