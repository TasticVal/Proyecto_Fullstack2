package cl.duoc.colegio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bff")
@CrossOrigin(origins = "*")
public class BffController {

    @Autowired
    private RestTemplate restTemplate;

    // Estos nombres de host corresponden a los nombres de servicio en tu docker-compose.yml
    private final String URL_ACADEMICO = "http://ms-academico:8080/api/estudiantes";
    private final String URL_ASISTENCIA = "http://ms-asistencia:8080/api/asistencia";

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardData() {
        Map<String, Object> response = new HashMap<>();
        
        // Llamada a ms-academico
        Object estudiantes = restTemplate.getForObject(URL_ACADEMICO, Object.class);
        
        // Llamada a ms-asistencia
        Object asistencias = restTemplate.getForObject(URL_ASISTENCIA, Object.class);
        
        response.put("estudiantes", estudiantes);
        response.put("asistencias", asistencias);
        
        return response;
    }
}