package IngestionAzureStorage.IngestionAzureStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class DocumentUploadController {

    private final DocumentUploaderService documentUploaderService;

    @Autowired
    public DocumentUploadController(DocumentUploaderService documentUploaderService) {
        this.documentUploaderService = documentUploaderService;
    }

    @PostMapping("/uploadFileToBlobToADLS")
    public String uploadFileToBlobToADLS(@RequestParam String localFilePath,
                               @RequestParam String adlsConnectionString,
                               @RequestParam String containerName,
                               @RequestParam String blobName) {
        try {
            documentUploaderService.uploadDocumentToADLS(new File(localFilePath), adlsConnectionString, containerName, blobName);
            return "File uploaded successfully to ADLS.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file to ADLS: " + e.getMessage();
        }
    }
    @PostMapping("/uploadFileToFileShareADLS")
    public String uploadFileToFileShareADLS(@RequestParam String localFilePath,
                               @RequestParam String adlsConnectionString,
                               @RequestParam String fileName,
                               @RequestParam String shareName) {
        try {
            documentUploaderService.uploadFileToShare(new File(localFilePath), fileName,adlsConnectionString, shareName);
            return "File uploaded successfully to ADLS.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file to ADLS: " + e.getMessage();
        }
    }


}
