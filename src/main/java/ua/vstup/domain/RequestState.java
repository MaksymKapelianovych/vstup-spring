package ua.vstup.domain;

public enum RequestState {
    ACTIVE("Active","Активна"),
    ACCEPTED("Accepted","Прийнята"),
    DISABLED("Disabled","Відмовлено"),
    BUDGET("Budget","Бюджет"),
    CONTRACT("Contract", "Контракт"),
    NOT_PASS("Not pass","Не пройшла");


    private String nameUa;
    private String nameEn;

    RequestState(String nameEn, String nameUa){
        this.nameEn = nameEn;
        this.nameUa = nameUa;
    }

    public String getNameByLocale(String locale){
        if(locale == null){
            return nameEn;
        }
        switch (locale){
            case "ua":
                return nameUa;
            default:
                return nameEn;
        }
    }
}
