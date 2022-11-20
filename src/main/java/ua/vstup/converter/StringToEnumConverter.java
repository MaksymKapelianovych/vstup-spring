package ua.vstup.converter;

import org.springframework.core.convert.converter.Converter;
import ua.vstup.domain.DocType;

public class StringToEnumConverter implements Converter<String, DocType> {
    @Override
    public DocType convert(String source) {
        return DocType.valueOf(source.toUpperCase());
    }
}
