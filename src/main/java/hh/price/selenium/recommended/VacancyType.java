package hh.price.selenium.recommended;

public enum VacancyType {
    STANDART(1), STANDARTPLUS(2), PREMIUM(3), ANONYMOUS(4);

    private final int value;

    VacancyType(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
