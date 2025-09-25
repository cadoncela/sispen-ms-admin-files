package co.gov.dane.file.infraestructure.adapters.input.rest;

import co.gov.dane.file.application.ports.input.FileServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Oliver & Ragnar
 */
@Slf4j
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequiredArgsConstructor
@RestController

public class FileRestAdapter {

    private final FileServicePort servicePort;

    @PreAuthorize("hasRole('sispen_capturador')")
    @PostMapping("/v1/api/upload/{codInterno}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String codInterno) throws IOException {
        return ResponseEntity.ok(servicePort.uploadFile(codInterno, file));
    }

    @PreAuthorize("hasRole('sispen_analista')")
    @GetMapping("/v1/api/download/{fileUrl}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileUrl){
        try {
            log.info("Entrando a descargar");
            Resource resource = servicePort.downloadFile(fileUrl);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
