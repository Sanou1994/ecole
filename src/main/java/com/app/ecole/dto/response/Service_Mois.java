package com.app.ecole.dto.response;

import com.app.ecole.entities.Type_Paiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Service_Mois
{
    private Type_Paiement typePaiement;
    private List<String> mois_non_payes = new ArrayList<>();

}
