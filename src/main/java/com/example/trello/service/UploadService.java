package com.example.trello.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void init();

    void save(MultipartFile file);

    Resource load(String filename);

    void deleteAll();
}
