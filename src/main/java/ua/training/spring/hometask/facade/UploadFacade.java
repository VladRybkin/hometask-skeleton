package ua.training.spring.hometask.facade;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFacade {

    void saveDataFromJsonFile(MultipartFile jsonFile) throws IOException;
}
