package cl.duoc.colegio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

@RestController
@RequestMapping("/api/bff")
@CrossOrigin(origins = "*")
public class BffController {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL_ACADEMICO = "http://ms-academico:8080/api/estudiantes";
    private final String URL_ASISTENCIA = "http://ms-asistencia:8080/api/asistencia";
    private final String URL_COMUNICACIONES = "http://ms-comunicaciones:8080/api/comunicaciones/resumen";

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardData() {
        Map<String, Object> response = new HashMap<>();
        
        // 1. Llamada a ms-academico (Protegida)
        try {
            response.put("estudiantes", restTemplate.getForObject(URL_ACADEMICO, Object.class));
        } catch (Exception e) {
            response.put("estudiantes", Collections.singletonList("Servicio académico no disponible"));
        }
        
        // 2. Llamada a ms-asistencia (Protegida)
        try {
            response.put("asistencias", restTemplate.getForObject(URL_ASISTENCIA, Object.class));
        } catch (Exception e) {
            response.put("asistencias", Collections.singletonList("Servicio de asistencia no disponible"));
        }
        
        // 3. Llamada a ms-comunicaciones (Protegida)
        try {
            response.put("comunicaciones", restTemplate.getForObject(URL_COMUNICACIONES, Object.class));
        } catch (Exception e) {
            response.put("comunicaciones", Collections.singletonList("Servicio de comunicaciones no disponible"));
        }
        
        return response;
    }
}