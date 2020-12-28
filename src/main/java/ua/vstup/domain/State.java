package ua.vstup.domain;

public enum State {
    ACTIVE("Active","Активна"),
    ACCEPTED("Accepted","Прийнята"),
    DISABLED("Disabled","Відмовлено"),
    BUDGET("Budget","Бюджет"),
    CONTRACT("Contract", "Контракт"),
    NOT_PASS("Not pass","Не пройшла");


    private String name_ua;
    private String name_en;

    State(String name_en, String name_ua){
        this.name_en = name_en;
        this.name_ua = name_ua;
    }
}
