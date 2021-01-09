package ua.vstup.controller.entrant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.vstup.service.FacultyService;
import ua.vstup.utility.ParameterParser;

@Controller
@RequestMapping("/entrant/faculty")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantFacultyController {
    private final FacultyService facultyService;

    @GetMapping("")
    public String get(Model model, @RequestParam(value = "page", required = false) String page,
                      @RequestParam(value = "sort", required = false) String sortBy,
                      @RequestParam(value = "nameBy", required = false) String nameBy){
        int totalPages = facultyService.pageCountActive();
        model.addAttribute("totalPages", totalPages);

        Sort sort = null;
        if(nameBy != null) {
            Sort.Direction direction = ParameterParser.parseSortType(sortBy);
            sort = Sort.by(direction, nameBy);
            model.addAttribute("sort", sortBy);
            model.addAttribute("nameBy", nameBy);
        }

        model.addAttribute("page", ParameterParser.parsePageNumber(page, 0, totalPages));
        model.addAttribute("faculties", facultyService.getAllActive(page, sort));
        return "entrant/faculty/faculty";
    }
}
