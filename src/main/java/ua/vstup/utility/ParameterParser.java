package ua.vstup.utility;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

@UtilityClass
public class ParameterParser {

    public static int parsePageNumber(String param, int defaultValue, int maxValue) {
        if (param == null) {
            return defaultValue;
        }
        try {
            int value = Integer.parseInt(param);
            if (value <= 0) {
                return 0;
            }
            if (value >= maxValue) {
                return maxValue - 1;
            }
            return value;
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    public static Sort.Direction parseSortType(String type){
        return type == null ? Sort.DEFAULT_DIRECTION : Sort.Direction.valueOf(type);
    }
}
