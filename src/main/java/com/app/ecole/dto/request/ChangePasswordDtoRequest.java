package com.app.ecole.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class ChangePasswordDtoRequest
{
	private UUID idUser;
	private String oldPassword;
	private String newPassword;
}
