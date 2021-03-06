package ua.training.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ua.training.spring.hometask.facade.UploadFacade;

import java.io.IOException;

@Controller
@RequestMapping(value = "/upload")
@PreAuthorize("hasAuthority('BOOKING_MANAGER')")
public class UploadController {

    @Autowired
    private UploadFacade uploadFacade;

    @GetMapping
    public String getUpload() {
        return "upload";
    }

    @PostMapping
    public String importDataFromJsonFile(@ModelAttribute MultipartFile jsonFile) throws IOException {
        uploadFacade.saveDataFromJsonFile(jsonFile);

        return "redirect:/upload";
    }
}
