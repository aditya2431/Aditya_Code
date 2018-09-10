package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by bbdnet10033 on 9/10/2018.
 */
public class BerlinTimeConverter implements TimeConverter {

    static ErrorMessages errorMessage = new ErrorMessages();

    @Override
    public String convertTime(String time) {

        if (time == null) {
            throw new RuntimeException(errorMessage.NO_INPUT);
        }

        String[] times = time.split(":", 3);


        if (times.length != 3) {
            throw new RuntimeException(errorMessage.INVALID_INPUT);
        }

        int hours, minutes, seconds = 0;

        try {
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
            seconds = Integer.parseInt(times[2]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(errorMessage.NUMERIC_TIME_ERROR);
        }

        if (hours < 0 || hours > 24) throw new RuntimeException(errorMessage.INVALID_HOURS);
        if (minutes < 0 || minutes > 59) throw new RuntimeException(errorMessage.INVALID_MINS);
        if (seconds < 0 || seconds > 59) throw new RuntimeException(errorMessage.INVALID_SECS);

        return processTime(hours, minutes, seconds);
    }

    private String processTime(int hours, int minutes, int seconds) {

        String line1 = (seconds % 2 == 0) ? "Y" : "O";
        String line2 = rowString(hours / 5, 4, "R").trim();
        String line3 = rowString(hours % 5, 4, "R").trim();
        String line4 = rowString(minutes / 5, 11, "Y").replaceAll("YYY", "YYR").trim();
        String line5 = rowString(minutes % 5, 4, "Y").trim();

        return String.join("\n", Arrays.asList(line1, line2, line3, line4, line5));

    }

    /**
     * Creates a string for each row of the berlin clock.
     *
     * @param onLights
     * @param lightsInRow
     * @param lampType
     * @returnn A string representing a single row of the clock.
     */
    private String rowString(int onLights, int lightsInRow, String lampType) {

        int offLights = lightsInRow - onLights;
        String glowing = String.join("", Collections.nCopies(onLights, lampType));
        String off = String.join("", Collections.nCopies(offLights, "O"));

        return glowing + off;
    }
}
