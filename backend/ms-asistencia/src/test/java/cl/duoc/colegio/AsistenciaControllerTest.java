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

@WebMvcTest(AsistenciaController.class)
class AsistenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AsistenciaRepository repository;

    @Test
    void testGetAll_DeberiaRetornarOk() throws Exception {
        mockMvc.perform(get("/api/asistencia"))
               .andExpect(status().isOk());
    }

    @Test
    void testCreate_DeberiaRetornarCreated() throws Exception {
        // Preparamos un objeto falso para el guardado
        Asistencia asistencia = new Asistencia();
        when(repository.save(any(Asistencia.class))).thenReturn(asistencia);

        // Simulamos el envío de datos JSON
        String jsonBody = "{\"estado\":\"Presente\", \"fecha\":\"2026-05-26\"}";

        mockMvc.perform(post("/api/asistencia")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isCreated());
    }
}