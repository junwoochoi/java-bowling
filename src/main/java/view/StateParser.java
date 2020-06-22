package view;

import domain.state.State;
import spark.utils.CollectionUtils;
import utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class StateParser {

    public String parse(State state) {
        if (CollectionUtils.isEmpty(state.getFallenPinsHistory())) {
            return StringUtils.EMPTY;
        }

        final List<String> mappedHistories = state.getFallenPinsHistory().stream()
                .map(numberOfPins -> HistoryMark.findMarkOrItSelf(numberOfPins, state.getClass()))
                .collect(Collectors.toList());


        FrameStringFormatterFactory stringFormatterFactory = new FrameStringFormatterFactory();
        StringFormatter formatter = stringFormatterFactory.create(state.getFallenPinsHistory().size());

        return formatter.format(mappedHistories);
    }
}
