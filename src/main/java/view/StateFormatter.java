package view;

import domain.state.State;
import domain.state.finish.Miss;
import domain.state.finish.Spare;
import domain.state.finish.Strike;
import domain.state.playing.NormalState;
import view.dto.StateDto;

import java.util.Arrays;
import java.util.function.Function;

import static utils.StringUtils.EMPTY;

public enum StateFormatter {

    STRIKE(Strike.class, stateDto -> convert(stateDto.getFirstHit())),
    SPARE(Spare.class, stateDto -> convert(stateDto.getFirstHit()) + OutputView.DELIMITER + Constants.SPARE_SYMBOL),
    MISS(Miss.class, stateDto -> convert(stateDto.getFirstHit()) + OutputView.DELIMITER + convert(stateDto.getSecondHit())),
    NORMAL_STATE(NormalState.class, stateDto -> convert(stateDto.getFirstHit()));


    private final Class<? extends State> stateType;
    private final Function<StateDto, String> converter;

    StateFormatter(final Class<? extends State> stateType, final Function<StateDto, String> converter) {
        this.stateType = stateType;
        this.converter = converter;
    }

    public static String format(StateDto stateDto) {
        return Arrays.stream(values())
                .filter(stateFormatter -> stateFormatter.stateType.equals(stateDto.getStateClass()))
                .findAny()
                .map(stateFormatter -> stateFormatter.converter.apply(stateDto))
                .orElseGet(() -> EMPTY);
    }

    private static String convert(int hit) {
        return SymbolConverter.convert(hit);
    }

    private static class Constants {
        public static final String SPARE_SYMBOL = "/";
    }
}
