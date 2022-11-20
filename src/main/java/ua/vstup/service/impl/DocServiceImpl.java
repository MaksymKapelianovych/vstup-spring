package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.vstup.domain.Doc;
import ua.vstup.domain.DocType;
import ua.vstup.domain.Entrant;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.DocRepository;
import ua.vstup.repository.EntrantRepository;
import ua.vstup.service.DocService;
import ua.vstup.service.FileSystemService;
import ua.vstup.service.mapper.DocMapper;
import ua.vstup.service.mapper.EntrantMapper;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocServiceImpl implements DocService {
    private final EntrantRepository entrantRepository;
    private final FileSystemService fileSystemService;
    private final DocRepository docRepository;
    private final DocMapper docMapper;
    private final EntrantMapper entrantMapper;


    @Override
    public void save(Doc doc, Entrant entrant, MultipartFile file) {
        doc.setPath(fileSystemService.store(file));
        if(docRepository.save(docMapper.mapToEntity(doc)).getId() == 0){
            throw new IncorrectDataException("");
        }

        switch (doc.getType()){
            case PASSPORT:
                entrant.setPassport(doc);
                break;
            case CERTIFICATION:
                entrant.setCertificate(doc);
                break;
            case ASSESSMENT:
                entrant.setAssessment(doc);
                break;
            case PHOTO:
                entrant.setPhoto(doc);
                break;
        }

        if (entrantRepository.save(entrantMapper.mapToEntity(entrant)).getId() == 0) {
             throw new IncorrectDataException("");
        }
    }

    @Override
    public Doc getDoc(Entrant entrant, DocType docType) {
        switch (docType){
            case PASSPORT:
                if(entrant.getPassport() == null){
                    Doc doc = new Doc();
                    doc.setType(docType);
                    return doc;
                }

                return entrant.getPassport();
            case CERTIFICATION:
                if(entrant.getCertificate() == null){
                    Doc doc = new Doc();
                    doc.setType(docType);
                    return doc;
                }

                return entrant.getCertificate();
            case ASSESSMENT:
                if(entrant.getAssessment() == null){
                    Doc doc = new Doc();
                    doc.setType(docType);
                    return doc;
                }

                return entrant.getAssessment();
            case PHOTO:
                if(entrant.getPhoto() == null){
                    Doc doc = new Doc();
                    doc.setType(docType);
                    return doc;
                }

                return entrant.getPhoto();
            default:
                throw new IncorrectDataException("");
        }
    }
}
