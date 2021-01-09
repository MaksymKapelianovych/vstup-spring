package ua.vstup.controller.entrant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.vstup.domain.Entrant;
import ua.vstup.domain.Faculty;
import ua.vstup.domain.Request;
import ua.vstup.service.EntrantService;
import ua.vstup.service.FacultyService;
import ua.vstup.service.RequestService;

@Controller
@RequestMapping("/entrant/request")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantRequestController {
    private final RequestService requestService;
    private final EntrantService entrantService;
    private final FacultyService facultyService;

    @GetMapping("")
    public String get(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Entrant entrant = entrantService.findByEmail(username);
        model.addAttribute("requests", requestService.getAllByEntrant(entrant));
        return "entrant/request/request";
    }

    @GetMapping("add/{id}")
    public String addPage(@PathVariable Integer id, Model model){
        Faculty faculty = facultyService.get(id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Entrant entrant = entrantService.findByEmail(username);

        model.addAttribute("faculty", faculty);
        model.addAttribute("subjects", requestService.jointSubjects(faculty.getRequirement(), entrant.getRequirement()));
        return "entrant/request/add";
    }

    @PostMapping("add")
    public String add(Request request){
        requestService.add(request);
        return "redirect:/entrant/request";
    }
}
