package com.nistagram.usermicroservice.verify_account;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("src");
        uploadPath = uploadPath.resolve("main");
        uploadPath = uploadPath.resolve("resources");
        uploadPath = uploadPath.resolve(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static String saveImage(MultipartFile multipartFile, String uploadDir) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }

    public static byte[] getImage(String fileName, String resourceDir) {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/main/resources/" + resourceDir + "/");

        File in = new File(filePath + fileName);
        try {
            return IOUtils.toByteArray(new FileInputStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
