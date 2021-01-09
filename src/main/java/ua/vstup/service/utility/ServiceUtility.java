package ua.vstup.service.utility;

import lombok.experimental.UtilityClass;
import java.util.function.IntFunction;


@UtilityClass
public class ServiceUtility {
    private static final String INCORRECT_DATA = "incorrect.data";

    public static int getNumberOfPage(long countOfRecords, int itemsPerPage) {
        return ((int) countOfRecords / itemsPerPage) + (countOfRecords % itemsPerPage == 0 ? 0 : 1);
    }
}
