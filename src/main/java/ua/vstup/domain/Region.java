package ua.vstup.domain;


public enum Region {
    KYIV("Kyiv", "Київ"),
    ODESA("Odesa", "Одеса"),
    LVIV("Lviv", "Львів"),
    KHARKIV("Kharkiv", "Харків"),
    CHERNIHIV("Chernihiv", "Чернігів");

    private String name_ua;
    private String name_en;

    Region(String name_en, String name_ua){
        this.name_en = name_en;
        this.name_ua = name_ua;
    }
}
