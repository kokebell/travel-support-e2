package com.example.demo.model;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileForm {
    private List<MultipartFile> multipartFile;

}