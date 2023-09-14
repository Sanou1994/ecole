package com.app.ecole.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupeDtoRequest
{
	private UUID ID;
	private String nomGroupe;
	private boolean status;
	private UUID groupeID;


}
