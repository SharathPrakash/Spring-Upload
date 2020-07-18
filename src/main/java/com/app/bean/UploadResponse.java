package com.app.bean;

import java.io.File;
import java.util.List;

public class UploadResponse {

	private List<String> description;
	private List<File> uploadedFiles;
	private List<String> failedFiles;
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public List<File> getUploadedFiles() {
		return uploadedFiles;
	}
	public void setUploadedFiles(List<File> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	public List<String> getFailedFiles() {
		return failedFiles;
	}
	public void setFailedFiles(List<String> failedFiles) {
		this.failedFiles = failedFiles;
	}
	
	
}
