package ua.vstup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ua.vstup.service.FileSystemService;
import ua.vstup.utility.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class VstupApplication {
    public static void main(String[] args) {
        SpringApplication.run(VstupApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileSystemService fileSystemService) {
        return (args) -> {
            fileSystemService.init();
        };
    }
}
