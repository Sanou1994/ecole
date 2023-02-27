package com.gestion_ecole.ecole.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gestion_ecole.ecole.dto.response.SupportPhysiqueDtoResponse;
import com.gestion_ecole.ecole.entities.Reponse;

@Service
public class FileService implements IFileService{
	@Value("${file.upload-dir}")
    private String fileStorageProperties;
   

    /**
     * UTILITAIRE DE CREATION DU REPERTOIRE (OU SOUS-REPERTOIRE) DE STOCKAGE SI
     * CELUI-CI EST INEXISTANT
     *
     * @param directoryRef
     * @return
     */
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
	public Reponse uploadFile(MultipartFile file,String type)
	{
		Reponse reponse =new Reponse();
		try
		{   
			
			
			switch (type) {
			case "PHOTO":
				if (!file.getOriginalFilename().toLowerCase().endsWith(".jpg")
		                && !file.getOriginalFilename().toLowerCase().endsWith(".jpeg")
		                && !file.getOriginalFilename().toLowerCase().endsWith(".png")
		                && !file.getOriginalFilename().toLowerCase().endsWith(".gif"))
		        {
			        reponse.setCode(201);
			    	reponse.setMessage(" Ce format de fichier n'est pas autorisé. Réessayez avec un .jpg, .jpeg, .png ou .gif SVP.");
		        }
			 else
			 {
				 Path destinationStockage = this.initStoragePath("photo");//on cree un subFolder dans le folder de storage
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
		            reponse.setResult( new SupportPhysiqueDtoResponse( filename,destinationStockage.toString(),type,new Date().getTime()));
			        reponse.setCode(200);
			    	reponse.setMessage(" la photo  a été bien chargée"); 
			 }
			
				break;
            case "TD":
				
				break;
            case "TP":
				
				break;
            case "EVALUATION":
				
				break;
			default:
				break;
			}
			
			
			
			 
				return reponse ;

           
        }
		catch (Exception e)
		{
			System.out.println(e.toString());
			reponse.setCode(500);
	    	reponse.setMessage(" Erreur survenue lors de l'enregistrement du fichier.");
			return reponse ;
        } 
	}
	
   
}
