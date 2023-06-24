package com.upc.TuCine.TuCine.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TypeUser")

public class TypeUser {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Schema(required = true)
    private Integer id;

    @Column(name = "name", length = 10, nullable = false)
    @Schema(description = "Nombre del tipo de usuario", example = "Cinéfilo", required = true)
    private String name;
}
