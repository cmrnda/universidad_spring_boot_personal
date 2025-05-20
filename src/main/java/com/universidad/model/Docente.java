package com.universidad.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "docente")
@Schema(description = "Representa un docente de la universidad, que puede tener evaluaciones vinculadas.")
public class Docente extends Persona {

    @Schema(description = "Número único asignado al docente para identificarlo.", example = "D12345")
    @Column(name = "nro_empleado", nullable = false, unique = true)
    private String nroEmpleado;

    @Schema(description = "Departamento al que pertenece el docente.", example = "Matemáticas")
    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Schema(description = "Lista de evaluaciones realizadas al docente.")
    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacionDocente> evaluaciones;
}