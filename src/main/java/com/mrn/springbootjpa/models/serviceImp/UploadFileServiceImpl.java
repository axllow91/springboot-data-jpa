package com.mrn.springbootjpa.models.serviceImp;

import com.mrn.springbootjpa.models.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private static final String UPLOAD_FOLDER = "uploads";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path imagePath = getPath(filename);
        log.info("path Image: " + imagePath);
        Resource resource = null;

        resource = new UrlResource((imagePath.toUri()));

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: Could not load properly the image: " + imagePath.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        // concatenate to filename an new randomUUID
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info("rootPath: " + rootPath);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }


    @Override
    public boolean delete(String filename) {
        // get the path of the image stored in upload folder
        Path rootPath = getPath(filename);
        // we transform this image into a file so we can delete it
        File file = rootPath.toFile();
        // check if we can delete it successfully
        // display a info message after successfully deleting the file
        if (file.exists() && file.canRead()) {
            return file.delete();
        }

        // could not delete it
        return false;
    }

    @Override
    public void deleteAll() {
        //FileSystemUtils -> Utility methods for working with the file system.
        // deleteRecursively() -> Delete the supplied File - for directories, recursively delete
        // any nested directories or files as well
        // deletes all files and directory with him
        FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        // create the directory
        Files.createDirectory(Paths.get(UPLOAD_FOLDER));
    }

    // get the path of upload folder
    private Path getPath(String filename) {
        return Paths.get(UPLOAD_FOLDER).resolve(filename).toAbsolutePath(); // make this the absolute path
    }
}
