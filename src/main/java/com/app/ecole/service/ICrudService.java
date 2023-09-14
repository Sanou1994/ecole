package com.app.ecole.service;
import com.app.ecole.entities.Reponse;

import java.util.UUID;

public interface ICrudService <T>{

    public Reponse create(T User);
    public Reponse getAll();
    public Reponse getById(UUID id);
    public Reponse disableById(UUID id);

}
