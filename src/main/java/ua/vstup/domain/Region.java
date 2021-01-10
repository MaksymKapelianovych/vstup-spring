package ua.vstup.domain;


public enum Region {
    KYIV("Kyiv", "Київ"),
    ODESA("Odesa", "Одеса"),
    LVIV("Lviv", "Львів"),
    KHARKIV("Kharkiv", "Харків"),
    CHERNIHIV("Chernihiv", "Чернігів");

    private String nameUa;
    private String nameEn;

    Region(String nameEn, String nameUa){
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
