package com.mrn.springbootjpa;

import com.mrn.springbootjpa.models.service.IUploadFileService;
import com.mrn.springbootjpa.models.serviceImp.UploadFileServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner  {

    private final IUploadFileService uploadFileService;

    public SpringbootJpaApplication(IUploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uploadFileService.deleteAll();
        uploadFileService.init();
    }
}
