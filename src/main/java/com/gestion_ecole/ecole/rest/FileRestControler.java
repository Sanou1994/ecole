package com.gestion_ecole.ecole.rest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.service.IFileService;
import com.gestion_ecole.ecole.utils.Utility;

@RestController
public class FileRestControler {
	@Autowired
	private IFileService fileService;
	@Value("${file.upload-dir}")
	private String fileStorageProperties;
	 //FILE
    @PostMapping(path = Utility.UPLOAD_FILE, headers = "Content-Type= multipart/form-data")
    public Reponse uploadFile(@RequestParam("file")  MultipartFile file) 
    {       
       return this.fileService.uploadFile(file,"PHOTO");           
    }
    @RequestMapping(value = Utility.GET_PHOTO, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException, URISyntaxException {
   try
   {
	Path file = Paths.get(fileStorageProperties+"/photo")
            .resolve(name);
	File f = file.toFile();
	if(f.exists())
	{
		byte[] arr = Files.readAllBytes(file);   	
	     

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
        .body(arr);	
	}
	else
	{
		return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
        .body(new byte[0]);	
	}
}
catch (Exception e) {
	return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
    .body(new byte[0]);	
   }
    	
    		
    }
   
}
