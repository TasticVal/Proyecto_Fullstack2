package cl.duoc.colegio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*") // <--- ESTA LÍNEA SOLUCIONA EL BLOQUEO CORS
public class EstudianteController {

    @Autowired
    private EstudianteRepository repository;

    @GetMapping
    public List<Estudiante> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Estudiante create(@RequestBody Estudiante estudiante) {
        return repository.save(estudiante);
    }
}