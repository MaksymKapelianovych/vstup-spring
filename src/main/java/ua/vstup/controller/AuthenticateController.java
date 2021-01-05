package ua.vstup.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.vstup.domain.Entrant;
import ua.vstup.domain.SubjectName;
import ua.vstup.service.EntrantService;
import ua.vstup.service.SchoolService;

import java.util.Objects;
import java.util.function.Predicate;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateController {
    private final SchoolService schoolService;
    private final EntrantService entrantService;

    @GetMapping("/login-page")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register-page")
    public String registerPage(Model model){
        model.addAttribute("subjectNames", SubjectName.values());
        model.addAttribute("schools", schoolService.getAll());
        return "register";
    }

    @PostMapping("/register")
    public String register(Entrant entrant){
        entrantService.register(entrant);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(Objects::toString)
                .anyMatch(Predicate.isEqual("ADMIN"))){
            return "admin/profile";
        }else{
            return "entrant/profile";
        }
    }

}
