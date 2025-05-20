package com.universidad.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
@Schema(description = "Entidad base que representa una persona en el sistema.")
public abstract class Persona {

    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la persona.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Version
    @Schema(description = "Versión de la entidad para el control de concurrencia.", example = "1")
    private Long version;

    @Column(nullable = false, length = 50)
    @Basic(optional = false)
    @Length(min = 3, max = 50)
    @Schema(description = "Nombre de la persona.", example = "Juan", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Column(nullable = false, length = 50)
    @Basic(optional = false)
    @Length(min = 3, max = 50)
    @Schema(description = "Apellido de la persona.", example = "Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    @Schema(description = "Correo electrónico único de la persona.", example = "juan.perez@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Basic(optional = false)
    @Schema(description = "Fecha de nacimiento de la persona.", example = "1990-05-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaNacimiento;
}