package com.app.ecole.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDataDtoResponse
{
    private String filename;
    private String type;
    private String url;
}
