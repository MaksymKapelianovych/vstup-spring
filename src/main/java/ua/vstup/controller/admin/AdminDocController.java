package ua.vstup.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.vstup.domain.Entrant;
import ua.vstup.service.DocService;
import ua.vstup.service.EntrantService;
import ua.vstup.service.FileSystemService;

@Controller
@RequestMapping("/admin/doc")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminDocController {
    private final DocService docService;
    private final FileSystemService fileSystemService;
    private final EntrantService entrantService;

    @GetMapping("load/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> loadFile(@PathVariable String filename) {

        Resource file = fileSystemService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("{id}")
    public String getDocs(@PathVariable Integer id, Model model){
        Entrant entrant = entrantService.findById(id);
        model.addAttribute("entrant", entrant);
        return "admin/entrant/doc";
    }
}
