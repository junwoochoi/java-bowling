package view;

import spark.utils.CollectionUtils;
import utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FrameHistoryParser {

    public String parse(List<Integer> frameHistories) {
        if (CollectionUtils.isEmpty(frameHistories)) {
            return StringUtils.EMPTY;
        }

        final List<String> mappedHistories = frameHistories.stream()
                .map(numberOfPins -> HistoryMark.findMarkOrItSelf(numberOfPins, frameHistories.size()))
                .collect(Collectors.toList());


        FrameStringFormatterFactory stringFormatterFactory = new FrameStringFormatterFactory();
        StringFormatter formatter = stringFormatterFactory.create(frameHistories.size());

        return formatter.format(mappedHistories);
    }
}
