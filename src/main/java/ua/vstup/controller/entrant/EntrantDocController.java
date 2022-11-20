package ua.vstup.controller.entrant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.vstup.controller.ControllerHelper;
import ua.vstup.domain.Doc;
import ua.vstup.domain.DocType;
import ua.vstup.domain.Entrant;
import ua.vstup.service.DocService;
import ua.vstup.service.EntrantService;

import javax.validation.Valid;

@Controller
@RequestMapping("/entrant/upload-doc")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EntrantDocController {

    private final DocService docService;
    private final EntrantService entrantService;

    @GetMapping("page/{type}")
    public String getPage(@PathVariable(value = "type") DocType docType, Model model){
//        Doc doc = new Doc();
//        doc.setType(docType);
//        DocType type = DocType.valueOf(docType);
        Entrant entrant = ControllerHelper.getEntrantFromSecurityContext(entrantService);
        Doc doc = docService.getDoc(entrant, docType);
        model.addAttribute("entrant", entrant);
        model.addAttribute("doc", doc);

        return "entrant/upload-doc";
    }

    @PostMapping("save")
    public String saveDoc(@RequestParam("file") MultipartFile file,
                          @Valid Doc doc,
                          BindingResult bindingResult,
                          Model model){
        if(bindingResult.hasErrors() || file.isEmpty()){
            model.addAttribute("doc", doc);
            return "entrant/upload-doc";
        }
        Entrant entrant = ControllerHelper.getEntrantFromSecurityContext(entrantService);
        docService.save(doc, entrant, file);
        return "redirect:/profile";
    }
}
