package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.vstup.domain.RequestState;
import ua.vstup.service.RequestService;
import ua.vstup.utility.ParameterParser;

@Controller
@RequestMapping("/admin/request")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminRequestController {
    private final RequestService requestService;

    @GetMapping("")
    public String get(Model model, @RequestParam(value = "page", required = false) String page){
        int totalPages = requestService.pageCount();
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("page", ParameterParser.parsePageNumber(page, 0, totalPages));
        model.addAttribute("requests", requestService.getAll(page));
        return "admin/request/request";
    }

    @GetMapping("accept/{id}")
    public String accept(@PathVariable("id") Integer id){
        requestService.updateStateById(id, RequestState.ACCEPTED);
        return "redirect:/admin/request";
    }

    @GetMapping("disable/{id}")
    public String disable(@PathVariable("id") Integer id){
        requestService.updateStateById(id, RequestState.DISABLED);
        return "redirect:/admin/request";
    }

}
