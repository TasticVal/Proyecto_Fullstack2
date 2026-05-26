package cl.duoc.colegio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ComunicacionesController.class)
class ComunicacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunicacionRepository repository;

    @Test
    void testGetResumen_DeberiaRetornarOk() throws Exception {
        mockMvc.perform(get("/api/comunicaciones/resumen"))
               .andExpect(status().isOk());
    }
}