package cl.duoc.colegio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudianteRepository repository;

    @Test
    void testGetAll_DeberiaRetornarOk() throws Exception {
        // Probamos GET /api/estudiantes
        mockMvc.perform(get("/api/estudiantes"))
               .andExpect(status().isOk());
    }

    @Test
    void testCreate_DeberiaRetornarCreated() throws Exception {
        // Preparamos un objeto falso
        Estudiante estudiante = new Estudiante();
        when(repository.save(any(Estudiante.class))).thenReturn(estudiante);

        // Simulamos el JSON (asegúrate de que los campos coincidan con tu clase Estudiante)
        String jsonBody = "{\"nombre\":\"Juan Perez\", \"rut\":\"12.345.678-9\"}";

        // Probamos POST /api/estudiantes
        mockMvc.perform(post("/api/estudiantes")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isCreated());
    }
}