package rent.tycoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent.tycoon.config.FileStorageProperties;

import java.io.File;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:5173")
public class FileController {
    private final FileStorageProperties fileStorageProperties;


    @Autowired
    public FileController(FileStorageProperties fileStorageProperties){
        this.fileStorageProperties = fileStorageProperties;
    }

    @GetMapping("/{productId}/{fileName}")
    public ResponseEntity<Resource> serveFile(@PathVariable long productId, @PathVariable String fileName) {
        String baseDir = fileStorageProperties.getUploadDir();

        File filePath = new File(baseDir, "ProductNumber_" + productId + File.separator + fileName);
        Resource resource = new FileSystemResource(filePath.toPath());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
