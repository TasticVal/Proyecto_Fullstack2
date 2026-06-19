package cl.duoc.colegio;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComunicacionesController.class)
class ComunicacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunicacionesRepository repository;

    @Test
    void testGetAll_DeberiaRetornarOk() throws Exception {
        mockMvc.perform(get("/api/comunicaciones"))
               .andExpect(status().isOk());
    }

    @Test
    void testGetById_CuandoExiste_DeberiaRetornarOk() throws Exception {
        Comunicaciones com = new Comunicaciones("Reunión", "Revisión de notas", "2026-06-18");
        com.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(com));

        mockMvc.perform(get("/api/comunicaciones/1"))
               .andExpect(status().isOk());
    }

    @Test
    void testGetById_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/comunicaciones/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_DeberiaRetornarCreated() throws Exception {
        Comunicaciones com = new Comunicaciones("Reunión", "Revisión de notas", "2026-06-18");
        when(repository.save(ArgumentMatchers.any(Comunicaciones.class))).thenReturn(com);
        String jsonBody = "{\"titulo\":\"Reunión\", \"mensaje\":\"Revisión de notas\", \"fecha\":\"2026-06-18\"}";

        mockMvc.perform(post("/api/comunicaciones")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isCreated());
    }

    @Test
    void testUpdate_CuandoExiste_DeberiaRetornarOk() throws Exception {
        Comunicaciones comExistente = new Comunicaciones("Antiguo", "Mensaje viejo", "2026-01-01");
        comExistente.setId(1L);
        Comunicaciones comActualizada = new Comunicaciones("Reunión", "Revisión de notas", "2026-06-18");

        when(repository.findById(1L)).thenReturn(Optional.of(comExistente));
        when(repository.save(ArgumentMatchers.any(Comunicaciones.class))).thenReturn(comActualizada);

        String jsonBody = "{\"titulo\":\"Reunión\", \"mensaje\":\"Revisión de notas\", \"fecha\":\"2026-06-18\"}";

        mockMvc.perform(put("/api/comunicaciones/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isOk());
    }

    @Test
    void testUpdate_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        String jsonBody = "{\"titulo\":\"Reunión\", \"mensaje\":\"Revisión de notas\", \"fecha\":\"2026-06-18\"}";

        mockMvc.perform(put("/api/comunicaciones/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isNotFound());
    }

    @Test
    void testDelete_CuandoExiste_DeberiaRetornarNoContent() throws Exception {
        when(repository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/comunicaciones/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    void testDelete_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
        when(repository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/comunicaciones/1"))
               .andExpect(status().isNotFound());
    }
}