package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ua.vstup.domain.Entrant;
import ua.vstup.domain.Role;
import ua.vstup.entity.EntrantEntity;
import ua.vstup.entity.RequirementEntity;
import ua.vstup.entity.RoleEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.EntrantRepository;
import ua.vstup.repository.RequirementRepository;
import ua.vstup.repository.SchoolRepository;
import ua.vstup.repository.SubjectRepository;
import ua.vstup.service.EntrantService;
import ua.vstup.service.mapper.EntrantMapper;
import ua.vstup.service.utility.ServiceUtility;
import ua.vstup.utility.ParameterParser;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantServiceImpl implements EntrantService {
    private static final int ITEMS_PER_PAGE = 3;

    private final EntrantRepository entrantRepository;
    private final SchoolRepository schoolRepository;

    private final EntrantMapper entrantMapper;

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(){
        String from = "sender@gmail.com";
        String to = "m.kapelianovych@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a plain text email");
        message.setText("Hello guys! This is a plain text email.");

        mailSender.send(message);
    }

    @Override
    public Entrant findById(Integer id) {
        return entrantMapper.mapToDomain(entrantRepository.findById(id)
                .orElseThrow(() -> new IncorrectDataException("")));
    }

    @Override
    public void register(Entrant entrant, Integer schoolId) {
        entrant.setActive(true);
        entrant.setRole(Role.USER);

        EntrantEntity entrantEntity = entrantMapper.mapToEntity(entrant);

        entrantEntity.setSchoolEntity(schoolRepository.findById(schoolId)
                .orElseThrow(() -> new IncorrectDataException("School doesn't exist")));

        if(entrantRepository.save(entrantEntity).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public List<Entrant> getAllEntrants(String page) {
        PageRequest pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE);
        return entrantRepository.findAllByRole(RoleEntity.USER, pageRequest).stream()
                .map(entrantMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public int pageCount() {
        EntrantEntity entity = new EntrantEntity();
        entity.setRole(RoleEntity.USER);
        return ServiceUtility.getNumberOfPage(entrantRepository.count(Example.of(entity)), ITEMS_PER_PAGE);
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
