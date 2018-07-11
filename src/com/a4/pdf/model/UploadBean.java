package com.a4.pdf.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadBean {
  private MultipartFile file;
  private String fileType;
public MultipartFile getFile() {
	return file;
}
public void setFile(MultipartFile file) {
	this.file = file;
}
public String getFileType() {
	return fileType;
}
public void setFileType(String fileType) {
	this.fileType = fileType;
}
  
	 
}
