package cl.duoc.colegio;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comunicaciones")
@CrossOrigin(origins = "*")
public class ComunicacionesController {

    private final ComunicacionesRepository repository;

    public ComunicacionesController(ComunicacionesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Comunicaciones> getAll() {
        return repository.findAll();
    }

    // 👉 NUEVO ENDPOINT PARA EL BFF
    @GetMapping("/resumen")
    public List<Comunicaciones> getResumen() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comunicaciones> getById(@PathVariable Long id) {
        Optional<Comunicaciones> com = repository.findById(id);
        return com.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comunicaciones> create(@Valid @RequestBody Comunicaciones com) {
        Comunicaciones nuevaCom = repository.save(com);
        return new ResponseEntity<>(nuevaCom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comunicaciones> update(@PathVariable Long id, @Valid @RequestBody Comunicaciones comDetails) {
        Optional<Comunicaciones> com = repository.findById(id);
        
        if (com.isPresent()) {
            Comunicaciones comExistente = com.get();
            comExistente.setTitulo(comDetails.getTitulo());
            comExistente.setMensaje(comDetails.getMensaje());
            comExistente.setFecha(comDetails.getFecha());
            
            Comunicaciones comActualizada = repository.save(comExistente);
            return ResponseEntity.ok(comActualizada);
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