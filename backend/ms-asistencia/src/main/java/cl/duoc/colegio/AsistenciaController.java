package cl.duoc.colegio;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    private final AsistenciaRepository repository;

    public AsistenciaController(AsistenciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Asistencia> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = repository.findById(id);
        return asistencia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asistencia> create(@Valid @RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = repository.save(asistencia);
        return new ResponseEntity<>(nuevaAsistencia, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> update(@PathVariable Long id, @Valid @RequestBody Asistencia asistenciaDetails) {
        Optional<Asistencia> asistencia = repository.findById(id);
        
        if (asistencia.isPresent()) {
            Asistencia asistenciaExistente = asistencia.get();
            asistenciaExistente.setEstado(asistenciaDetails.getEstado());
            asistenciaExistente.setFecha(asistenciaDetails.getFecha());
            
            Asistencia asistenciaActualizada = repository.save(asistenciaExistente);
            return ResponseEntity.ok(asistenciaActualizada);
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