package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vstup.domain.Faculty;
import ua.vstup.domain.SubjectName;
import ua.vstup.service.FacultyService;
import ua.vstup.utility.ParameterParser;

@Controller
@RequestMapping("/admin/faculty")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminFacultyController {
    private final FacultyService facultyService;

    @GetMapping("")
    public String getAll(Model model,
                         @RequestParam(value = "page", required = false) String page,
                         @RequestParam(value = "sort", required = false) String sortBy,
                         @RequestParam(value = "nameBy", required = false) String nameBy){
        int totalPages = facultyService.pageCount();
        model.addAttribute("totalPages", totalPages);

        Sort sort = null;
        if(nameBy != null) {
            Sort.Direction direction = ParameterParser.parseSortType(sortBy);
            sort = Sort.by(direction, nameBy);
            model.addAttribute("sort", sortBy);
            model.addAttribute("nameBy", nameBy);
        }

        model.addAttribute("page", ParameterParser.parsePageNumber(page, 0, totalPages));

        model.addAttribute("faculties", facultyService.getAll(page, sort));
        return "admin/faculty/faculty";
    }

    @GetMapping("add")
    public String addPage(Model model){
        model.addAttribute("subjectNames", SubjectName.values());
        return "admin/faculty/add";
    }

    @PostMapping("add")
    public String add(Faculty faculty){
        facultyService.add(faculty);
        return "redirect:/admin/faculty";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        facultyService.delete(id);
        return "redirect:/admin/faculty";
    }

    @GetMapping("edit/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        model.addAttribute("subjectNames", SubjectName.values());
        model.addAttribute("faculty", facultyService.get(id));
        return "admin/faculty/edit";
    }

    @PostMapping("edit")
    public String edit(Faculty faculty){
        facultyService.edit(faculty);
        return "redirect:/admin/faculty";
    }
}
