package com.gestion_ecole.ecole.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gestion_ecole.ecole.dto.response.SupportPhysiqueDtoResponse;
import com.gestion_ecole.ecole.entities.Reponse;
import com.gestion_ecole.ecole.repository.SupportPhysiqueRepository;

@Service
public class SupportPhysiqueService implements ISupportPhysiqueService{
	@Value("${file.upload-dir}")
    private String fileStorageProperties;
    
    //	injection des Mapper et Repository
    @Autowired
    private SupportPhysiqueRepository supportPhysiqueRepository;
    
    
  

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
		            Files.copy(file.getInputStream(), destinationStockage, StandardCopyOption.REPLACE_EXISTING);
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
	@Override
	public Reponse load(String filename)
	{
		Reponse reponse = new Reponse();
		try
		{  
			 Path file = Paths.get(fileStorageProperties+"/logo")
                     .resolve(filename);
             Resource resource = new UrlResource(file.toUri());

           if (resource.exists() || resource.isReadable())
           {
        	reponse.setCode(200);
   	    	reponse.setMessage(" Le fichier  a été obtenu avec succès");
   	    	
   	    	
   	    	reponse.setResult(resource.getInputStream().toString());
           } else 
           {
        	   reponse.setCode(200);
      	    	reponse.setMessage(" Le fichier est inexistent");
      	    	reponse.setResult(resource);
           }
		    
			return reponse ;
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	   	    reponse.setMessage(" Une erreur interne est survenue");
			return reponse ;
		}
		
		
		
	}
	@Override
	public Reponse deleteAll() {
		 FileSystemUtils.deleteRecursively(Paths.get(fileStorageProperties)
                 .toFile());
		return null;
	}
	@Override
	public Reponse loadAll() {
		 try {
	            Path root = Paths.get(fileStorageProperties);
	            if (Files.exists(root)) {
	                return (Reponse) Files.walk(root, 1)
	                            .filter(path -> !path.equals(root))
	                            .collect(Collectors.toList());
	            }

	            return (Reponse) Collections.emptyList();
	        } catch (IOException e) {
	            throw new RuntimeException("Could not list the files!");
	        }
	}
	@Override
	public Reponse deleteFile(String url)
	{
		Reponse reponse = new Reponse();
		try
		{  
			 Path fileToDeletePath = Paths.get(url);
			// Delete file or directory if it exists
			 boolean isDeleted = Files.deleteIfExists(fileToDeletePath);
			 
			 if(isDeleted)
			 {
			    	reponse.setMessage(" Le fichier   a été supprimé avec succès");
 
			 }
			 else
			 {
			    	reponse.setMessage("Le fichier n'existe plus");
 
			 } 
			 
		    reponse.setCode(200);
	    	reponse.setResult(isDeleted);

			return reponse ;
		}
		catch (Exception e) 
		{
			reponse.setCode(500);
	   	    reponse.setMessage(" Une erreur interne est survenue");
			return reponse ;
		} 	
		
	}

   
   
}
