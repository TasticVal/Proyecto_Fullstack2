package cl.duoc.colegio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<Asistencia> create(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = repository.save(asistencia);
        return new ResponseEntity<>(nuevaAsistencia, HttpStatus.CREATED);
    }
}
