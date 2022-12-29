package com.gestion_ecole.ecole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Reponse {
private String message;
private int code;
private Object result;
}
