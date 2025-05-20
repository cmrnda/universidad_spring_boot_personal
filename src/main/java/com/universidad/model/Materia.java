package com.universidad.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "materia")
@Schema(description = "Entidad que representa una materia en el sistema de gestión universitaria.")
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_materia")
    @Schema(description = "Identificador único de la materia.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Column(name = "nombre_materia", nullable = false, length = 100)
    @Schema(description = "Nombre de la materia.", example = "Matemáticas Básicas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreMateria;

    @Column(name = "codigo_unico", nullable = false, unique = true)
    @Schema(description = "Código único que identifica a la materia.", example = "MATH-101", requiredMode = Schema.RequiredMode.REQUIRED)
    private String codigoUnico;

    @Column(name = "creditos", nullable = false)
    @Schema(description = "Cantidad de créditos académicos de la materia.", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer creditos;

    @Version
    @Schema(description = "Versión de la entidad para el control de concurrencia.", example = "1")
    private Long version;

    @ManyToOne
    @JoinColumn(name = "id_docente_asignado", referencedColumnName = "id_persona")
    @Schema(description = "Docente asignado como responsable de la materia.")
    private Docente docenteAsignado;

    @ManyToMany
    @JoinTable(
            name = "materia_prerequisito",
            joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_prerequisito")
    )
    @Schema(description = "Lista de materias que son prerequisitos para esta materia.")
    private List<Materia> prerequisitos;

    @ManyToMany(mappedBy = "prerequisitos")
    @Schema(description = "Materias para las que esta materia sirve como prerequisito.")
    private List<Materia> esPrerequisitoDe;

    /**
     * Constructor adicional requerido para manejar ciertas operaciones.
     */
    public Materia(Long id, String nombreMateria, String codigoUnico) {
        this.id = id;
        this.nombreMateria = nombreMateria;
        this.codigoUnico = codigoUnico;
    }

    /**
     * Verifica si agregar la materia con el ID dado como prerequisito formaría un ciclo.
     *
     * @param prerequisitoId ID de la materia candidata a prerequisito.
     * @return true si se formaría un ciclo, false en caso contrario.
     */
    @Schema(hidden = true)
    public boolean formariaCirculo(Long prerequisitoId) {
        return formariaCirculoRecursivo(this.getId(), prerequisitoId, new java.util.HashSet<>());
    }

    @Schema(hidden = true)
    private boolean formariaCirculoRecursivo(Long objetivoId, Long actualId, java.util.Set<Long> visitados) {
        if (objetivoId == null || actualId == null) return false;
        if (objetivoId.equals(actualId)) return true;
        if (!visitados.add(actualId)) return false;
        if (this.getPrerequisitos() == null) return false;
        for (Materia prereq : this.getPrerequisitos()) {
            if (prereq != null && prereq.getId() != null && prereq.getId().equals(actualId)) {
                if (prereq.getPrerequisitos() != null) {
                    for (Materia subPrereq : prereq.getPrerequisitos()) {
                        if (formariaCirculoRecursivo(objetivoId, subPrereq.getId(), visitados)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}