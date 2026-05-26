package cl.duoc.colegio.controller;

import cl.duoc.colegio.Comunicacion; // Importa la clase nueva
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/comunicaciones")
@CrossOrigin(origins = "*")
public class ComunicacionesController {

    @GetMapping("/resumen")
    public List<Comunicacion> getResumen() {
        // Simulamos datos reales
        return Arrays.asList(
            new Comunicacion(1L, "Bienvenida", "Bienvenido al semestre 2026-1"),
            new Comunicacion(2L, "Aviso", "Recuerda revisar tus notas en el portal")
        );
    }
}