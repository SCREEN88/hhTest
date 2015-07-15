package hh.price.selenium.purchaseServices;

public enum VacancyType {
    STANDART(1, "Стандарт"),
    STANDART_PLUS(2, "Стандарт плюс"),
    PREMIUM(3, "Премиум"),
    ANONYMOUS(4, "аноним");

    private int    value;
    private String name;

    VacancyType(int i, String name) {
        value = i;

        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
