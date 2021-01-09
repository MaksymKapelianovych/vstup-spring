package ua.vstup.controller.entrant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.vstup.service.FacultyService;

@Controller
@RequestMapping("entrant/faculty")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantFacultyController {
    private final FacultyService facultyService;

    @GetMapping("/")
    public String get(Model model){
        model.addAttribute("faculties", facultyService.getAllActive());
        return "entrant/faculty/faculty";
    }
}
