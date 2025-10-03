package co.gov.dane.file.application.ports.input;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Oliver & Ragnar
 */
public interface FileServicePort {

    String uploadFile(String codInternoMeta, MultipartFile file) throws IOException;

    Resource downloadFile (String url);
}
