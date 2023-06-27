package com.upc.TuCine.TuCine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TypeUserDto {
    @Schema(required = true)
    private Integer id;

    @Schema(description = "Nombre del tipo de usuario", example = "Cinéfilo", required = true)
    private String name;
}
