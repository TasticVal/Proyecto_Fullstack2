package cl.duoc.colegio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteRepository repository;

    @GetMapping
    public List<Estudiante> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getById(@PathVariable Long id) {
        Optional<Estudiante> estudiante = repository.findById(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudiante> create(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = repository.save(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @Valid @RequestBody Estudiante estudianteDetails) {
        Optional<Estudiante> estudiante = repository.findById(id);
        
        if (estudiante.isPresent()) {
            Estudiante estudianteExistente = estudiante.get();
            estudianteExistente.setNombre(estudianteDetails.getNombre());
            estudianteExistente.setRut(estudianteDetails.getRut());
            
            Estudiante estudianteActualizado = repository.save(estudianteExistente);
            return ResponseEntity.ok(estudianteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}