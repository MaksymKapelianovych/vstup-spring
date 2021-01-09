package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vstup.domain.Faculty;
import ua.vstup.domain.SubjectName;
import ua.vstup.service.FacultyService;

@Controller
@RequestMapping("admin/faculty")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminFacultyController {
    private final FacultyService facultyService;

    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("faculties", facultyService.getAll());
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
