package ua.vstup.controller.entrant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vstup.controller.ControllerHelper;
import ua.vstup.domain.*;
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
        Entrant entrant = ControllerHelper.getEntrantFromSecurityContext(entrantService);
        requestService.checkIfRequestExists(entrant, faculty);

        model.addAttribute("faculty", faculty);
        model.addAttribute("subjects", requestService.jointSubjects(faculty.getRequirement(), entrant.getRequirement()));
        model.addAttribute("request", new Request());
        return "entrant/request/add";
    }

    @PostMapping("add/{id}")
    public String add(@RequestParam(value = "firstSubject") Integer firstSubject,
                      @RequestParam(value = "secondSubject") Integer secondSubject,
                      @RequestParam(value = "thirdSubject") Integer thirdSubject,
                      @PathVariable("id") Integer facultyId){

        Entrant entrant = ControllerHelper.getEntrantFromSecurityContext(entrantService);

        requestService.add(entrant, facultyId, firstSubject, secondSubject, thirdSubject);
        return "redirect:/entrant/request";
    }
}
