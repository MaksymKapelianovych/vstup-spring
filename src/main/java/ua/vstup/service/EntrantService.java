package ua.vstup.service;

import org.springframework.security.authentication.AuthenticationProvider;
import ua.vstup.domain.Entrant;
import ua.vstup.domain.Subject;

import java.util.List;

public interface EntrantService extends AuthenticationProvider {
    void register(Entrant entrant, Integer schoolId);

    List<Entrant> getAllEntrants(String page);
    int pageCount();

    void disable(Integer id);
    void enable(Integer id);

    Entrant findByEmail(String username);
}
