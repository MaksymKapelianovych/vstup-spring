package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.vstup.service.StatementService;

@Controller
@RequestMapping("/admin/statement")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StatementController {
    private final StatementService statementService;

    @GetMapping("")
    public String get(Model model){
        model.addAttribute("statement", statementService.getUnfinalizedStatement());
        return "admin/statement/statement";
    }

    @GetMapping("finalize")
    public String finalizeStatement(){
        statementService.finalizeStatement();
        return "redirect:/admin/statement";
    }

    @GetMapping("add")
    public String addStatement(){
        statementService.createStatement();
        return "redirect:/admin/statement";
    }
}
