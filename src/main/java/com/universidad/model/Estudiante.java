package com.universidad.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "estudiante")
@Schema(description = "Entidad que representa a un estudiante matriculado en el sistema universitario.")
public class Estudiante extends Persona { // Define la clase Estudiante que extiende de Persona

    @Schema(description = "Número único de inscripción del estudiante.", example = "20230001", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "numero_inscripcion", nullable = false, unique = true)
    private String numeroInscripcion;

    @Schema(description = "Estado actual del estudiante (activo, inactivo, etc.).", example = "Activo")
    @Column(name = "estado")
    private String estado;

    @Schema(description = "Usuario que dio de alta al estudiante.", example = "admin")
    @Column(name = "usuario_alta")
    private String usuarioAlta;

    @Schema(description = "Fecha en la que se dio de alta al estudiante.", example = "2023-01-15", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    @Basic(optional = false)
    private LocalDate fechaAlta;

    @Schema(description = "Usuario que realizó la última modificación en el registro del estudiante.", example = "admin")
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Schema(description = "Fecha en la que se realizó la última modificación del registro del estudiante.", example = "2023-03-01")
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    @Basic(optional = true)
    private LocalDate fechaModificacion;

    @Schema(description = "Usuario que dio de baja al estudiante.", example = "admin")
    @Column(name = "usuario_baja")
    private String usuarioBaja;

    @Schema(description = "Fecha en la que se dio de baja al estudiante.", example = "2023-05-01")
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    @Basic(optional = true)
    private LocalDate fechaBaja;

    @Schema(description = "Motivo de baja del estudiante (renuncia, deserción, traslado, etc.).", example = "Traslado a otra universidad")
    @Column(name = "motivo_baja")
    private String motivoBaja;

    @Schema(description = "Lista de materias en las que el estudiante está inscrito.")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "estudiante_materia",
            joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_materia")
    )
    private List<Materia> materias;
}