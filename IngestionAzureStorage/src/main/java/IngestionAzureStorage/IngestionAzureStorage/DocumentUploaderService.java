package IngestionAzureStorage.IngestionAzureStorage;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.ShareFileClientBuilder;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DocumentUploaderService {

    public void uploadDocumentToADLS(File localFile, String adlsConnectionString, String containerName, String blobName) {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(adlsConnectionString)
                .containerName(containerName)
                .buildClient();
        containerClient.getBlobClient(blobName).uploadFromFile(localFile.getAbsolutePath());
    }

    public void uploadFileToShare(File localFile, String fileName, String connectionString, String shareName) {
        ShareFileClient fileClient = new ShareFileClientBuilder()
                .connectionString(connectionString)
                .shareName(shareName)
                .resourcePath(fileName)
                .buildFileClient(); // Correct method to build a ShareFileClient

        fileClient.create(localFile.length()); // Create the file
        fileClient.uploadFromFile(localFile.getAbsolutePath()); // Upload the file
    }
}
