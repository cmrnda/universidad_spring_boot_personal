package com.universidad.controller;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.model.Materia;
import com.universidad.service.IEstudianteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@Validated
public class EstudianteController {

    private final IEstudianteService estudianteService;
    private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @Operation(summary = "Inscribir materias a un estudiante", description = "Permite inscribir un listado de materias a un estudiante específico basado en su ID.")
    @PostMapping("/{id}/inscribir")
    @Transactional
    public ResponseEntity<EstudianteDTO> inscribirMateria(
            @Parameter(description = "ID del estudiante", required = true, example = "1") @PathVariable Long id,
            @Parameter(description = "Lista de IDs de materias a inscribir", required = true) @RequestBody List<Long> materiasIds) {

        logger.info("[ESTUDIANTE] Iniciando inscripción de materias para estudiante ID: {}", id);
        EstudianteDTO actualizado = estudianteService.inscribirMaterias(id, materiasIds);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Obtener todos los estudiantes", description = "Devuelve una lista de todos los estudiantes registrados.")
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerTodosLosEstudiantes: {}", inicio);
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerTodosLosEstudiantes: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(estudiantes);
    }

    @Operation(summary = "Obtener estudiante por número de inscripción", description = "Retorna los datos del estudiante relacionado con el número de inscripción proporcionado.")
    @GetMapping("/inscripcion/{numeroInscripcion}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorNumeroInscripcion(
            @Parameter(description = "Número de inscripción del estudiante", required = true, example = "20230001") @PathVariable String numeroInscripcion) {

        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerEstudiantePorNumeroInscripcion: {}", inicio);
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorNumeroInscripcion(numeroInscripcion);
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerEstudiantePorNumeroInscripcion: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(estudiante);
    }

    @Operation(summary = "Obtener materias de un estudiante", description = "Obtiene un listado de materias asociadas al estudiante dado su ID.")
    @GetMapping("/{id}/materias")
    public ResponseEntity<List<Materia>> obtenerMateriasDeEstudiante(
            @Parameter(description = "ID del estudiante", required = true, example = "1") @PathVariable("id") Long estudianteId) {

        List<Materia> materias = estudianteService.obtenerMateriasDeEstudiante(estudianteId);
        return ResponseEntity.ok(materias);
    }

    @Operation(summary = "Obtener un estudiante con bloqueo", description = "Obtiene los datos del estudiante mediante su ID utilizando bloqueo pesimista para evitar condiciones de carrera.")
    @GetMapping("/{id}/lock")
    public ResponseEntity<Estudiante> getEstudianteConBloqueo(
            @Parameter(description = "ID del estudiante", required = true, example = "1") @PathVariable Long id) {

        Estudiante estudiante = estudianteService.obtenerEstudianteConBloqueo(id);
        return ResponseEntity.ok(estudiante);
    }

    @Operation(summary = "Crear un estudiante", description = "Registra un nuevo estudiante en el sistema.")
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EstudianteDTO> crearEstudiante(
            @Parameter(description = "Datos del estudiante a registrar", required = true) @Valid @RequestBody EstudianteDTO estudianteDTO) {

        EstudianteDTO nuevoEstudiante = estudianteService.crearEstudiante(estudianteDTO);
        return ResponseEntity.status(201).body(nuevoEstudiante);
    }

    @Operation(summary = "Actualizar estudiante", description = "Actualiza los datos de un estudiante específico basado en su ID.")
    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(
            @Parameter(description = "ID del estudiante a actualizar", required = true, example = "1") @PathVariable Long id,
            @Parameter(description = "Nuevos datos del estudiante", required = true) @RequestBody EstudianteDTO estudianteDTO) {

        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @Operation(summary = "Dar de baja un estudiante", description = "Permite dar de baja a un estudiante especificando su ID y el motivo.")
    @PutMapping("/{id}/baja")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EstudianteDTO> eliminarEstudiante(
            @Parameter(description = "ID del estudiante a dar de baja", required = true, example = "1") @PathVariable Long id,
            @Parameter(description = "Datos necesarios para la baja, como el motivo", required = true) @RequestBody EstudianteDTO estudianteDTO) {

        EstudianteDTO estudianteEliminado = estudianteService.eliminarEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(estudianteEliminado);
    }

    @Operation(summary = "Listar estudiantes activos", description = "Obtiene todos los estudiantes que tienen el estado 'activo'.")
    @GetMapping("/activos")
    public ResponseEntity<List<EstudianteDTO>> obtenerEstudianteActivo() {
        List<EstudianteDTO> estudiantesActivos = estudianteService.obtenerEstudianteActivo();
        return ResponseEntity.ok(estudiantesActivos);
    }
}