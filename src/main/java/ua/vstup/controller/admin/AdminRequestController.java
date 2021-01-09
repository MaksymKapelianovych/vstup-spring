package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.vstup.service.RequestService;

@Controller
@RequestMapping("admin/request")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminRequestController {
    private final RequestService requestService;

    @GetMapping("")
    public String get(Model model){
        model.addAttribute("requests", requestService.getAll());
        return "admin/request/request";
    }


}
