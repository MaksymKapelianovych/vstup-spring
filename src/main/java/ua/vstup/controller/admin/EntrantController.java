package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vstup.service.EntrantService;

@Controller
@RequestMapping("/admin/entrant")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantController {
    private final EntrantService entrantService;

    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("entrants", entrantService.getAllEntrants());
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
