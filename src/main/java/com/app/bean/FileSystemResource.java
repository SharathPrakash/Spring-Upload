package com.app.bean;

import org.springframework.core.io.ByteArrayResource;

public class FileSystemResource extends ByteArrayResource {

	 private String fileName;

	    public FileSystemResource(byte[] byteArray , String filename) {
	        super(byteArray);
	        this.fileName = filename;
	    }

	    public String getFilename() { return fileName; }
	    public void setFilename(String fileName) { this.fileName= fileName; }

	    
}
