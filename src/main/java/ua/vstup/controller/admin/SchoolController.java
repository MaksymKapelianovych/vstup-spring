package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vstup.domain.Faculty;
import ua.vstup.domain.School;
import ua.vstup.domain.SubjectName;
import ua.vstup.service.SchoolService;
import ua.vstup.utility.ParameterParser;

@Controller
@RequestMapping("/admin/school")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("")
    public String getAll(Model model,
                         @RequestParam(value = "page", required = false) String page,
                         @RequestParam(value = "sort", required = false) String sortBy,
                         @RequestParam(value = "nameBy", required = false) String nameBy){
        int totalPages = schoolService.pageCount();
        model.addAttribute("totalPages", totalPages);

        Sort sort = null;
        if(nameBy != null && !nameBy.isEmpty()){
            sort = Sort.by(ParameterParser.parseSortType(sortBy), nameBy);
            model.addAttribute("sort", sortBy);
            model.addAttribute("nameBy", nameBy);
        }


        model.addAttribute("page", ParameterParser.parsePageNumber(page, 0, totalPages));

        model.addAttribute("schools", schoolService.getAll(page, sort));
        return "admin/school/school";
    }

    @GetMapping("history/{uniqueSchoolId}")
    public String getAllHistory(@PathVariable Integer uniqueSchoolId, Model model,
                         @RequestParam(value = "page", required = false) String page,
                         @RequestParam(value = "sort", required = false) String sortBy,
                         @RequestParam(value = "nameBy", required = false) String nameBy){
        int totalPages = schoolService.pageCountForHistory(uniqueSchoolId);
        model.addAttribute("totalPages", totalPages);

        Sort sort = null;
        if(nameBy != null && !nameBy.isEmpty()){
            sort = Sort.by(ParameterParser.parseSortType(sortBy), nameBy);
            model.addAttribute("sort", sortBy);
            model.addAttribute("nameBy", nameBy);
        }


        model.addAttribute("page", ParameterParser.parsePageNumber(page, 0, totalPages));

        model.addAttribute("schools", schoolService.getAllHistory(uniqueSchoolId, page, sort));
        return "admin/school/history";
    }

    @GetMapping("add")
    public String addPage(Model model){
        model.addAttribute("school", new School());
        return "admin/school/add";
    }

    @PostMapping("add")
    public String add(School school){
        schoolService.add(school);
        return "redirect:/admin/school";
    }

    @GetMapping("edit/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        model.addAttribute("school", schoolService.get(id));

        return "admin/school/edit";
    }

    @PostMapping("edit")
    public String edit(School school){
        schoolService.edit(school);
        return "redirect:/admin/school";
    }
}
