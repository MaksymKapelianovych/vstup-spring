package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vstup.service.EntrantService;
import ua.vstup.utility.ParameterParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Controller
@RequestMapping("/admin/entrant")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantController {
    private final EntrantService entrantService;

    @GetMapping("")
    public String getAll(Model model, @RequestParam(value = "page", required = false) String page){
        int totalPages = entrantService.pageCount();
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("page", ParameterParser.parsePageNumber(page, 0, totalPages));
        model.addAttribute("entrants", entrantService.getAllEntrants(page));
        return "admin/entrant/entrant";
    }

    @GetMapping("enable/{id}")
    public String enable(@PathVariable Integer id){
        entrantService.enable(id);
        return "redirect:/admin/entrant";
    }

    @GetMapping("disable/{id}")
    public String disable(@PathVariable Integer id){
        entrantService.disable(id);
        return "redirect:/admin/entrant";
    }
}
