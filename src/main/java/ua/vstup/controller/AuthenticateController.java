package ua.vstup.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.vstup.domain.*;
import ua.vstup.service.EntrantService;
import ua.vstup.service.SchoolService;
import ua.vstup.service.mapper.UserInfoEntrantMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateController {
    private final SchoolService schoolService;
    private final EntrantService entrantService;

    private final UserInfoEntrantMapper entrantMapper;

    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register-page")
    public String registerPage(Model model){
//        model.addAttribute("subjectNames", SubjectName.values());
        model.addAttribute("userInfo", new UserInfo());
        return "register";
    }

//    @PostMapping("/register")
//    public String register(Entrant entrant){
//        entrantService.register(entrant, 0);
//        return "redirect:/";
//    }

    @PostMapping("/register")
    public String register(UserInfo entrant){
        entrantService.register(entrantMapper.mapToDomain(entrant), entrant.getSchool_id());
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(Objects::toString)
                .anyMatch(Predicate.isEqual("ADMIN"))){
            return "admin/profile";
        }else{
            Entrant entrant = ControllerHelper.getEntrantFromSecurityContext(entrantService);
            model.addAttribute("entrant", entrant);
            return "entrant/profile";
        }
    }

    @ModelAttribute("schools")
    public List<School> schools() {
        return schoolService.getAll();
    }

    @ModelAttribute("subjectNames")
    public SubjectName[] subjectNames() {
        return SubjectName.values();
    }

}
