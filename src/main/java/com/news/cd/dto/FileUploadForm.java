package com.news.cd.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 25, 2016
 */

public class FileUploadForm {
    private CommonsMultipartFile fileDatas;
    private String description;

    public CommonsMultipartFile getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(CommonsMultipartFile fileDatas) {
        this.fileDatas = fileDatas;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
