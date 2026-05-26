package cl.duoc.colegio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = repository.save(estudiante);
        // Devolvemos 201 Created, es mucho más profesional
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }
}