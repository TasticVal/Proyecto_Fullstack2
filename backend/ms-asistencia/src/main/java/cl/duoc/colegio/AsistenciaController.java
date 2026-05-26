package cl.duoc.colegio;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "*") // <--- ¡Esto es lo que necesitabas!
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
    public Asistencia create(@RequestBody Asistencia asistencia) {
        return repository.save(asistencia);
    }
}