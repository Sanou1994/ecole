package com.app.ecole.service;

import com.app.ecole.dto.request.StructureDtoRequest;
import com.app.ecole.dto.response.FileDataDtoResponse;
import com.app.ecole.dto.response.StructureDtoResponse;
import com.app.ecole.entities.Reponse;
import com.app.ecole.entities.Structure;
import com.app.ecole.repository.IDaoStructure;
import com.app.ecole.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StructureService implements ICrudService <StructureDtoRequest> {
    @Autowired
    private IFileService fileService;
    @Autowired
    private IDaoStructure daoStructure;
    @Autowired(required=true)
    private ModelMapper modelMapper;

    @Override
    public Reponse create(StructureDtoRequest structureDtoRequest) {
        Reponse reponse = new Reponse();
        try
        {
            if(structureDtoRequest.getID() != null)
            {

                Optional<Structure> isExits= daoStructure.findByID(structureDtoRequest.getID());

                if(isExits.isPresent())
                {



                    if(structureDtoRequest.getNom() != null && !structureDtoRequest.getNom().equals(isExits.get().getNom()))
                    {
                        isExits.get().setNom(structureDtoRequest.getNom());
                    }

                    if(structureDtoRequest.getPays() != null && !structureDtoRequest.getPays().equals(isExits.get().getPays()))
                    {
                        isExits.get().setPays(structureDtoRequest.getPays());
                    }
                    if(structureDtoRequest.getAdresse() != null && !structureDtoRequest.getAdresse().equals(isExits.get().getAdresse()))
                    {
                        isExits.get().setAdresse(structureDtoRequest.getAdresse());
                    }

                    if(structureDtoRequest.getAdresse() != null && !structureDtoRequest.getAdresse().equals(isExits.get().getAdresse()))
                    {
                        isExits.get().setAdresse(structureDtoRequest.getAdresse());
                    }
                    if(structureDtoRequest.getEmail() != null && !structureDtoRequest.getEmail().equals(isExits.get().getEmail()))
                    {
                        isExits.get().setEmail(structureDtoRequest.getEmail());
                    }
                    if(structureDtoRequest.getTelephone() != null && !structureDtoRequest.getTelephone().equals(isExits.get().getTelephone()))
                    {
                        isExits.get().setTelephone(structureDtoRequest.getTelephone());
                    }


                    Structure groupe1 = daoStructure.save(isExits.get());
                    var groupeSave =modelMapper.map(groupe1, StructureDtoResponse.class);
                    reponse.setMessage("La structure a été modifiés avec succès");
                    reponse.setResult(groupeSave);
                    reponse.setCode(200);


                }
                else
                {

                    reponse.setMessage("Cette structure n'existe plus !");
                    reponse.setCode(201);


                }


            }
            else
            {

                reponse.setMessage("La structure inexistante");
                reponse.setCode(201);



            }
        }
        catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setMessage("Une erreur interne est survenue coté serveur  !" );
        }

        return reponse ;
    }
    public Reponse createFile(StructureDtoRequest structureDtoRequest, MultipartFile logo,MultipartFile favicon)
    {
        Reponse reponse = new Reponse();
        try
        {
            var groupe1 =modelMapper.map(structureDtoRequest, Structure.class);
           if(logo != null && favicon!=null &&  !logo.isEmpty() && logo.getSize() > 0 && !favicon.isEmpty() && favicon.getSize() > 0)
           {
             var lgo= fileService.uploadFile(logo, Utility.LOGO);
            var fav = fileService.uploadFile(favicon, Utility.FAVICON);




             if(lgo.getCode() == 200)
             {
                 var l=(FileDataDtoResponse)lgo.getResult();
                 groupe1.setLogo(l.getFilename());
                 groupe1.setUrl_logo(l.getUrl());

             }
               if(fav.getCode() == 200)
               {
                   var l=(FileDataDtoResponse)fav.getResult();
                   groupe1.setFavicon(l.getFilename());
                   groupe1.setUrl_favicon(l.getUrl());
               }



           }
            if( logo == null && favicon!=null  && !favicon.isEmpty() && favicon.getSize() > 0)
           {
               var fav = fileService.uploadFile(favicon, Utility.FAVICON);

               if(fav.getCode() == 200)
               {
                   var l=(FileDataDtoResponse)fav.getResult();
                   groupe1.setFavicon(l.getFilename());
                   groupe1.setUrl_favicon(l.getUrl());
               }
           }
            if(logo != null && favicon ==null &&  !logo.isEmpty() && logo.getSize() >0)
           {
               var lgo= fileService.uploadFile(logo, Utility.LOGO);
               if(lgo.getCode() == 200)
               {
                   var l=(FileDataDtoResponse)lgo.getResult();
                   groupe1.setLogo(l.getFilename());
                   groupe1.setUrl_logo(l.getUrl());

               }

           }
            Structure groupe2 = daoStructure.save(groupe1);
            var groupeSave =modelMapper.map(groupe2, StructureDtoResponse.class);
            reponse.setMessage("La structure a été enregistrée avec succès");
            reponse.setResult(groupeSave);
            reponse.setCode(200);
        }
        catch (Exception e)
        {
            reponse.setCode(500);
            reponse.setMessage("Une erreur interne est survenue coté serveur  !" +e.getMessage() );
        }

        return reponse ;
    }
    @Override
    public Reponse getAll() {
        Reponse reponse = new Reponse();
        try
        {

            List<StructureDtoResponse> users= daoStructure.findAll()
                    .stream()
                    .map(p->modelMapper.map(p,StructureDtoResponse.class)

                    ).collect(Collectors.toList());

            reponse.setResult(users);
            reponse.setMessage("Liste des structures !");
            reponse.setCode(200);



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }

    @Override
    public Reponse getById(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Structure> IsExistGroupe=daoStructure.findById(id);
            IsExistGroupe.ifPresentOrElse(
                    (userP)->{

                        var result=modelMapper.map(IsExistGroupe.get(), StructureDtoResponse.class);

                        if(userP.getUrl_logo() !=null && userP.getUrl_logo().length() > 0)
                        {

                            Path file = Paths.get(userP.getUrl_logo());
                            File f = file.toFile();
                            byte[] arr = null;
                            if(f.exists())
                            {
                                try {
                                    arr = Files.readAllBytes(file);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    arr=	new byte[0];
                                }
                            }
                            else
                            {
                                arr=	new byte[0];
                            }
                            result.setLogo(Base64.getEncoder().encodeToString(arr));


                        }
                        if(userP.getUrl_favicon() !=null && userP.getUrl_favicon().length() > 0)
                        {

                            Path file = Paths.get(userP.getUrl_favicon());
                            File f = file.toFile();
                            byte[] arr = null;
                            if(f.exists())
                            {
                                try {
                                    arr = Files.readAllBytes(file);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    arr=	new byte[0];
                                }
                            }
                            else
                            {
                                arr=	new byte[0];
                            }
                            result.setFavicon(Base64.getEncoder().encodeToString(arr));
                        }
                        reponse.setResult(result);
                        reponse.setMessage("La structure a été retrouvée avec succès");
                        reponse.setCode(200);


                    },
                    ()->{reponse.setMessage("La structure n'existe pas");
                        reponse.setCode(201);}
            );



        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }

    @Override
    public Reponse disableById(UUID id) {
        Reponse reponse = new Reponse();
        try
        {
            Optional<Structure> IsExistGroupe=daoStructure.findById(id);

            if(IsExistGroupe.isPresent())
            {
                IsExistGroupe.get().setStatus(!IsExistGroupe.get().isStatus());
                Structure userSave=daoStructure.save( IsExistGroupe.get());
                reponse.setResult(modelMapper.map(userSave, StructureDtoResponse.class));
                reponse.setMessage("Cette structure a été bloqué avec succès");
                reponse.setCode(200);

            }
            else
            {
                reponse.setMessage("Cette structure n'existe pas");
                reponse.setCode(201);

            }


        }
        catch (Exception e) {
            reponse.setMessage("Une erreur interne est survenue");
            reponse.setCode(500);

        }

        return reponse;
    }
}
