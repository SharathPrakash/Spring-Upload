package com.app.service;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.app.bean.FileSystemResource;
import com.app.bean.MyUploadForm;
import com.app.bean.UploadResponse;

@PropertySource("classpath:application.properties")
@Service
public class UploadService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring.rest.url}")
	private String restUrl;

	public String uploadService(HttpServletRequest request, Model model, //
			MyUploadForm myUploadForm) throws IOException {

		System.out.println("Service is called");

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		System.out.println("Service is called");

		
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		
		for (MultipartFile file : myUploadForm.getFileDatas()) { 
			   System.out.println(file.getOriginalFilename());
			    Resource resouceFile = new FileSystemResource(file.getBytes(), file.getOriginalFilename());
			   body.add("files",  resouceFile);
			   body.add("description", myUploadForm.getDescription());
			}
		
		//body.add("files", myUploadForm.getFileDatas());
		
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		System.out.println("requestEntity ::- " + requestEntity);
		

		System.out.println("Rest URL ::- " + restUrl);

		UploadResponse uploadResponse =  null;
		try {
			
			uploadResponse = restTemplate.postForObject(restUrl, requestEntity, UploadResponse.class);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// UploadResponse uploadResponse = restTemplate.exchange(restUrl,
		// HttpMethod.POST, entity, UploadResponse.class).getBody();

		model.addAttribute("description", uploadResponse.getDescription());
		model.addAttribute("uploadedFiles", uploadResponse.getUploadedFiles());
		model.addAttribute("failedFiles", uploadResponse.getFailedFiles());

		return "uploadResult";

	}
}
