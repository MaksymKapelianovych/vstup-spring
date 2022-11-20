package ua.vstup.domain;


public enum Region {
    KYIV1("Kyiv city", "Київ"),
    KYIV2("Kyiv region", "Київська область"),
    ODESA("Odesa region", "Одеська область"),
    LVIV("Lviv region", "Львівська область"),
    KHARKIV("Kharkiv region", "Харківська область"),
    CHERNIHIV("Chernihiv region", "Чернігівська область"),
    SUMY("Sumy region", "Сумська область"),
    DONETSK("Donetsk region", "Донецька область"),
    LUHANSK("Luhansk region", "Луганська область"),
    POLTAVA("Poltava region", "Полтавська область"),
    KIROVOHRAD("Kirovohrad region", "Кіровоградська область"),
    ZAPORIZHZHIA("Zaporizhzhia region", "Запорізька область"),
    DNIPRO("Dnipro region", "Дніпровська область"),
    KHERSON("Kherson region", "Херсонська область"),
    MYKOLAIV("Mykolaiv region", "Миколаївська область"),
    RIVNE("Zaporizhzhia region", "Рівненська область"),
    VOLYN("Volyn region", "Волинська область"),
    ZAKARPATTIA("Zakarpattia region", "Закарпатська область"),
    TERNOPIL("Ternopil region", "Тернопільська область"),
    IVANOFRANKIVSK("Ivanofrankivsk region", "Івано-Франківська область"),
    KHMELNYTSKYI("Khmelnytskyi region", "Хмельницька область"),
    KRYM("Krym region", "Кримська область"),
    CHERKASY("Cherkasy region", "Черкаська область"),
    CHERNIVTSI("Chernivtsi region", "Чернівецька область"),
    VINNYTSIA("Vinnytsia region", "Вінницька область");

    private String name_ua;
    private String name_en;

    Region(String name_en, String name_ua){
        this.name_en = name_en;
        this.name_ua = name_ua;
    }

    public String getNameByLocale(String locale){
        if(locale == null){
            return name_en;
        }
        switch (locale){
            case "ua":
                return name_ua;
            default:
                return name_en;
        }
    }
}
