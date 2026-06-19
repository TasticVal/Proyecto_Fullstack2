package cl.duoc.colegio;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

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
    void testGetById_CuandoExiste_DeberiaRetornarOk() throws Exception {
        Asistencia asistencia = new Asistencia("Presente", "2026-05-26");
        asistencia.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(asistencia));

        mockMvc.perform(get("/api/asistencia/1"))
               .andExpect(status().isOk());
    }

    @Test
    void testGetById_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/asistencia/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_DeberiaRetornarCreated() throws Exception {
        Asistencia asistencia = new Asistencia("Presente", "2026-05-26");
        when(repository.save(any(Asistencia.class))).thenReturn(asistencia);
        String jsonBody = "{\"estado\":\"Presente\", \"fecha\":\"2026-05-26\"}";

        mockMvc.perform(post("/api/asistencia")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isCreated());
    }

    @Test
    void testUpdate_CuandoExiste_DeberiaRetornarOk() throws Exception {
        Asistencia asistenciaExistente = new Asistencia("Ausente", "2026-05-26");
        asistenciaExistente.setId(1L);

        Asistencia asistenciaActualizada = new Asistencia("Presente", "2026-05-26");

        when(repository.findById(1L)).thenReturn(Optional.of(asistenciaExistente));
        when(repository.save(any(Asistencia.class))).thenReturn(asistenciaActualizada);

        String jsonBody = "{\"estado\":\"Presente\", \"fecha\":\"2026-05-26\"}";

        mockMvc.perform(put("/api/asistencia/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isOk());
    }

    @Test
    void testUpdate_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        String jsonBody = "{\"estado\":\"Presente\", \"fecha\":\"2026-05-26\"}";

        mockMvc.perform(put("/api/asistencia/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isNotFound());
    }

    @Test
    void testDelete_CuandoExiste_DeberiaRetornarNoContent() throws Exception {
        when(repository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/asistencia/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    void testDelete_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
        when(repository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/asistencia/1"))
               .andExpect(status().isNotFound());
    }
}