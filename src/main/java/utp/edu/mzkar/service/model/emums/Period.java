package utp.edu.mzkar.service.model.emums;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum Period {

    POWSZEDNI("-POWSZEDNI-"),
    SOBOTA("-SOBOTA-"),
    NIEDZIELA("-NIEDZIELA-"),
    NIEDZIELA_HANDLOWA("-NIEDZIELA_HANDLOWA-"),
    FERIE("-FERIE-");

    private final String value;

    Period(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static Period currentPeriod() {
        LocalDate now = LocalDate.now ();
        if ( DayOfWeek.SATURDAY.equals ( now.getDayOfWeek () ) ) {
            return Period.SOBOTA;
        } else if ( DayOfWeek.SUNDAY.equals ( now.getDayOfWeek () ) ) {
            return Period.NIEDZIELA;
        } else if ( now.isAfter ( LocalDate.of ( now.getYear (), 7, 1 ) ) && now.isBefore ( LocalDate.of ( now.getYear (), 8, 31 ) ) ) {
            return Period.FERIE;
        } else {
            return Period.POWSZEDNI;
        }
    }
}
