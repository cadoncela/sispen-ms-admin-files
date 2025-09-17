package co.gov.dane.file.application.service;

import co.gov.dane.file.application.ports.input.FileServicePort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Oliver & Ragnar
 */
@Service
@RequiredArgsConstructor
public class FileService implements FileServicePort {

    @Value("${sispen.folder.url-base}")
    private String baseUrl;

    //2023-2027.1.1.1.1

    @Override
    public String uploadFile(String codInternoMeta, MultipartFile file) throws IOException {
        String codInterno = Arrays.stream(StringUtils.splitByWholeSeparator(codInternoMeta, "."))
                .collect(Collectors.joining("-")).concat("-");
        String urlFile = codInterno.concat(file.getOriginalFilename());
        Path filePathValidator = Paths.get(baseUrl.concat(urlFile));
        if(Files.exists(filePathValidator)){
            return "Error. El archivo ya existe";
        }

        Path uploadPath = Paths.get(baseUrl);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Path filePath = uploadPath.resolve(urlFile);
        // Escribir el contenido del archivo en el disco
        Files.write(filePath, file.getBytes());
        return filePath.toAbsolutePath().toString();
    }
}
