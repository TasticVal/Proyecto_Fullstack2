package cl.duoc.colegio;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comunicaciones")
@CrossOrigin(origins = "*")
public class ComunicacionesController {

    private final ComunicacionRepository repository;

    public ComunicacionesController(ComunicacionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/resumen")
    public List<Comunicacion> getResumen() {
        return repository.findAll();
    }
}