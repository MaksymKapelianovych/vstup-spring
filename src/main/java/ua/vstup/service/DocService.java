package ua.vstup.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ua.vstup.domain.Doc;
import ua.vstup.domain.DocType;
import ua.vstup.domain.Entrant;

import java.nio.file.Path;

public interface DocService {
    void save(Doc doc, Entrant entrant, MultipartFile file);

    Doc getDoc(Entrant entrant, DocType docType);
}
