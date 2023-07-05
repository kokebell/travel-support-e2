package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileForm;

@Controller
public class FileController {

    @GetMapping("/imgup")
    String index(Model model) {
        model.addAttribute("fileForm", new FileForm());
        return "/imgup";
    }

    @PostMapping("/upload")
    String upload(
    		Model model, FileForm fileForm) {
    	
        List<MultipartFile> mfile = fileForm.getMultipartFile();
        mfile.forEach( f -> { 
        	//ファイル名を現在時刻で初期化する
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        	LocalDateTime ldt = LocalDateTime.now();
        	String initializedName = ldt.format(dtf);
        	//ファイル名の初期化に用いるファイルフォーマットを取得する
        	String fileName = f.getOriginalFilename();
        	fileName.indexOf(".");
        	String fileFormat = fileName.substring(fileName.indexOf("."));
        	//指定した文字列からファイルパスを作成する
        	Path filePath = Paths.get("C:/pleiades/2022-12/workspace/travel-support-e/src/main/resources/static/img/" + initializedName + fileFormat);
        	//ファイルを取得する
        	try {
            	Files.copy(f.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	File fileImg = new File(filePath.toString());
    		String str = fileImg.getAbsolutePath();
    		System.out.println("path : " + str);
        });
        
        return "redirect:/imgup";
    }
}