package com.example.demo.model;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {
    private List<MultipartFile> mFile;

	public List<MultipartFile> getMultipartFile() {
		return mFile;
	}
	public void setMultipartFile(List<MultipartFile> multipartFile) {
		this.mFile = multipartFile;
	}
}