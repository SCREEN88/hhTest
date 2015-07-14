package hh.price.selenium.recommended.additionalSubTabs;

public enum AdditionalServicesSubTabs {
    RESUME_SPOTLIGHT(1),
    ADVERTISING_ON_THE_SITE(2),
    PERSONAL_IN_THE_BALTIC_STATES(3),
    COMPANY_PROMOTION(4);

    private int tab;

    AdditionalServicesSubTabs(int tab) {
        this.tab = tab;
    }

    public int getTabNumber() {
        return tab;
    }
}
