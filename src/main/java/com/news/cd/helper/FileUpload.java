package com.news.cd.helper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.news.cd.constants.ApplicationConstant;
import com.news.cd.utils.EncodeUtils;

public class FileUpload {
    private static final String[] ALLOWED_FILE_TYPES = {"image/jpeg", "image/jpg", "image/gif"};
    private static final Long MAX_FILE_SIZE = 1048576L; //1MB
    
    public String process(MultipartFile file, String destination) {
        if (!file.isEmpty()) {
            String contentType = file.getContentType().toString().toLowerCase();
            if (isValidContentType(contentType)) {
                if (belowMaxFileSize(file.getSize())) {
                	// Create name for image
                	String renamed = EncodeUtils.md5(String.valueOf(new Random(100000).nextInt() + 10) 
                			+ file.getOriginalFilename()); 
                	String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                    String newFile = ApplicationConstant.REAL_PATH + destination + renamed + "." + extension;
                    System.out.println(newFile);
                    try {
                        file.transferTo(new File(newFile));
                        return destination.replaceAll("\\\\", "/") + renamed + "." + extension;
                    } catch (IOException e) {
                    	// Return to default destination
                    }
                } else {
                	// Return to default destination
                }
            } else {
            	// Return to default destination
            }
        } else {
        	// Return to default destination
        }
        return "/resources/client/images/trash/24.png";
    }
    
    private Boolean isValidContentType(String contentType) {
        if (!Arrays.asList(ALLOWED_FILE_TYPES).contains(contentType)) {
            return false;
        }
        return true;
    }
    
    private Boolean belowMaxFileSize(Long fileSize) {
        if (fileSize > MAX_FILE_SIZE) {
            return false;
        }
        return true;
    }
}