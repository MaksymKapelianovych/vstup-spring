package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import ua.vstup.exception.UploadFileException;
import ua.vstup.service.FileSystemService;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.vstup.utility.StorageProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemServiceImpl implements FileSystemService {
     private final Path root;

    @Autowired
    public FileSystemServiceImpl(StorageProperties properties) {
        this.root = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new UploadFileException("Failed to store empty file.");
            }
            Path destinationFile = this.root.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(root.toAbsolutePath())) {
                throw new UploadFileException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return destinationFile.toString();
            }
        } catch (IOException e) {
            throw new UploadFileException("Failed to store file.", e);
        }

    }

    @Override
    public Path load(String filename) {
        return root.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new UploadFileException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new UploadFileException("Could not read file: " + filename, e);
        }
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        }
        catch (IOException e) {
            throw new UploadFileException("Could not initialize storage", e);
        }
    }

}
