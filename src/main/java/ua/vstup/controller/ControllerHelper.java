package ua.vstup.controller;

import lombok.experimental.UtilityClass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.vstup.domain.Doc;
import ua.vstup.domain.DocType;
import ua.vstup.domain.Entrant;
import ua.vstup.service.EntrantService;

import java.nio.file.Paths;

@UtilityClass
public class ControllerHelper {
    public static Entrant getEntrantFromSecurityContext(EntrantService service) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.findByEmail(username);
    }

    private String getFileName(Doc doc){
        return Paths.get(doc.getPath()).getFileName().toString();
    }
}

