package ua.vstup.domain;

public enum SubjectName {
    MATH("Math", "Математика"),
    UKRAINIAN("Ukrainian", "Українська мова"),
    ENGLISH("English","Англійська мова"),
    HISTORY("History", "Історія"),
    PHYSICS("Physics","Фізика");

    private String nameUa;
    private String nameEn;

    SubjectName(String nameEn, String nameUa){
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
