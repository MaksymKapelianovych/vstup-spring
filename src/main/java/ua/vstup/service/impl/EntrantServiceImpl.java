package ua.vstup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ua.vstup.domain.Entrant;
import ua.vstup.entity.EntrantEntity;
import ua.vstup.entity.RoleEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.EntrantRepository;
import ua.vstup.service.EntrantService;
import ua.vstup.service.mapper.EntrantMapper;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
public class EntrantServiceImpl implements EntrantService {
    @Autowired
    private EntrantRepository entrantRepository;
    @Autowired
    private EntrantMapper entrantMapper;

    @Override
    public void register(Entrant entrant) {
        if(entrantRepository.save(entrantMapper.mapToEntity(entrant)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public void edit(Entrant entrant) {

    }

    @Override
    public List<Entrant> getAllEntrants() {
        return entrantRepository.findAllByRole(RoleEntity.USER).stream()
                .map(entrantMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void disable(Integer id) {
        EntrantEntity entrantEntity = entrantRepository.findById(id)
                .orElseThrow(() -> new IncorrectDataException("Entrant doesn't exist"));
        entrantEntity.setActive(false);
        if (entrantRepository.save(entrantEntity).getId() == 0) {
            throw new IncorrectDataException("");
        }
    }

    @Override
    public void enable(Integer id) {
        EntrantEntity entrantEntity = entrantRepository.findById(id)
                .orElseThrow(() -> new IncorrectDataException("Entrant doesn't exist"));
        entrantEntity.setActive(true);
        if (entrantRepository.save(entrantEntity).getId() == 0) {
            throw new IncorrectDataException("");
        }
    }

    @Override
    public Entrant findByEmail(String email) {
        return entrantMapper.mapToDomain(entrantRepository.findByEmail(email).
                orElseThrow(() -> new IncorrectDataException("user.does.not.exists")));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        EntrantEntity userEntity = entrantRepository.findByEmail(email).
                orElseThrow(() -> new IncorrectDataException("user.does.not.exists"));
        if(!userEntity.getActive()){
            throw new IncorrectDataException("Entrant is blocked");
        }

        if (password.equals(userEntity.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword(), singletonList(new SimpleGrantedAuthority(userEntity.getRole().name())));
        }

        throw new IncorrectDataException("incorrect.password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
