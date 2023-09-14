package com.app.ecole.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

import com.app.ecole.dto.response.FileDataDtoResponse;
import com.app.ecole.entities.Reponse;
import com.app.ecole.utils.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements IFileService{
	@Value("${file.upload-dir}")
    private String fileStorageProperties;

    private Path initStoragePath(String subFolder) {
        Path rootPath = Paths.get(fileStorageProperties + subFolder + File.separatorChar).toAbsolutePath().normalize();
        try {
            Files.createDirectories(rootPath);
            return rootPath;
        } catch (IOException e) {
            throw new RuntimeException("Echec de création du dossier de stockage des fichiers.");
        }
    }
    //	delete
   	@Override
	public Reponse uploadFile(MultipartFile file, String type)
	{
		Reponse reponse =new Reponse();

		try
		{
			if (!file.getOriginalFilename().toLowerCase().endsWith(".jpg")
		                && !file.getOriginalFilename().toLowerCase().endsWith(".jpeg")
		                && !file.getOriginalFilename().toLowerCase().endsWith(".png")
		                && !file.getOriginalFilename().toLowerCase().endsWith(".gif"))
		        {
					reponse.setCode(201);
					reponse.setMessage("Veuillez charger un logo ou favicon d'extension jpg,jpeg,png,gif");
				}
			 else
			 {
				 Path destinationStockage = this.initStoragePath(type);//on cree un subFolder dans le folder de storage
				 // Initialisation, enregistrement de meta-donnees et stockage du fichier
		            String filename = StringUtils.cleanPath(file.getOriginalFilename());// Normalisation du nom du fichier
		            destinationStockage = destinationStockage.resolve(filename);//formalise le path concatene du filename
		            //transfert du fichier de la source locale vers la destination systeme
		            //on le remplace s'il existe un fichier de meme nom
		            try {
						Files.copy(file.getInputStream(), destinationStockage, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 reponse.setCode(200);
                 reponse.setResult(new FileDataDtoResponse(filename,type,destinationStockage.toString()));
			 }


           
        }
		catch (Exception e)
		{
			reponse.setCode(500);

        }
return  reponse;

	}

	@Override
	public Reponse createAllDirectories() {
		Reponse reponse =new Reponse();		
		Path rootPathLogo = Paths.get(fileStorageProperties + Utility.LOGO + File.separatorChar).toAbsolutePath().normalize();
		Path rootPathFavicon = Paths.get(fileStorageProperties + Utility.FAVICON + File.separatorChar).toAbsolutePath().normalize();
		try 
		{
			// CREATION DES FICHIERS
			Files.createDirectories(rootPathLogo);
			Files.createDirectories(rootPathFavicon);
			
			Path fileLogo = Paths.get(rootPathLogo.toString());
			Path fileFavicon = Paths.get(rootPathFavicon.toString());

			File logo = fileLogo.toFile();
			File favicon = fileFavicon.toFile();

			if(logo.exists()
					&& favicon.exists())
            {
				reponse.setMessage(" Tous les repectoires ");
				reponse.setCode(200);
            }     
			else
			{
					reponse.setCode(201);

			}	
			
		} catch (Exception e) 
		{
			reponse.setMessage("Une erreur est survenue : "+e.getMessage());
			reponse.setCode(200);

		}
		return reponse;
	
	}
	
   
}
