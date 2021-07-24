package utp.edu.mzkar.service.model.emums;

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
}
