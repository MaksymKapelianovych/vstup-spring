package ua.vstup.controller;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.vstup.domain.Entrant;
import ua.vstup.service.EntrantService;

@UtilityClass
public class ControllerHelper {
    public static Entrant getEntrantFromSecurityContext(EntrantService service) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.findByEmail(username);
    }
}