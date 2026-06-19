package cl.duoc.colegio;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest({EstudianteController.class})
class EstudianteControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private EstudianteRepository repository;

   @Test
   void testGetAll_DeberiaRetornarOk() throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/api/estudiantes"))
            .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   void testGetById_CuandoExiste_DeberiaRetornarOk() throws Exception {
      Estudiante estudiante = new Estudiante("Juan Perez", "12.345.678-9");
      estudiante.setId(1L);
      Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(estudiante));

      this.mockMvc.perform(MockMvcRequestBuilders.get("/api/estudiantes/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   void testGetById_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
      Mockito.when(this.repository.findById(1L)).thenReturn(Optional.empty());

      this.mockMvc.perform(MockMvcRequestBuilders.get("/api/estudiantes/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
   }

   @Test
   void testCreate_DeberiaRetornarCreated() throws Exception {
      Estudiante estudiante = new Estudiante("Juan Perez", "12.345.678-9");
      Mockito.when(this.repository.save(ArgumentMatchers.any(Estudiante.class))).thenReturn(estudiante);
      String jsonBody = "{\"nombre\":\"Juan Perez\", \"rut\":\"12.345.678-9\"}";
      
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/estudiantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody))
            .andExpect(MockMvcResultMatchers.status().isCreated());
   }

   @Test
   void testUpdate_CuandoExiste_DeberiaRetornarOk() throws Exception {
      Estudiante estudianteExistente = new Estudiante("Juan", "11.111.111-1");
      estudianteExistente.setId(1L);
      
      Estudiante estudianteActualizado = new Estudiante("Juan Actualizado", "12.345.678-9");
      
      Mockito.when(this.repository.findById(1L)).thenReturn(Optional.of(estudianteExistente));
      Mockito.when(this.repository.save(ArgumentMatchers.any(Estudiante.class))).thenReturn(estudianteActualizado);

      String jsonBody = "{\"nombre\":\"Juan Actualizado\", \"rut\":\"12.345.678-9\"}";

      this.mockMvc.perform(MockMvcRequestBuilders.put("/api/estudiantes/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody))
            .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   void testUpdate_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
      Mockito.when(this.repository.findById(1L)).thenReturn(Optional.empty());
      String jsonBody = "{\"nombre\":\"Inexistente\", \"rut\":\"00.000.000-0\"}";

      this.mockMvc.perform(MockMvcRequestBuilders.put("/api/estudiantes/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
   }

   @Test
   void testDelete_CuandoExiste_DeberiaRetornarNoContent() throws Exception {
      Mockito.when(this.repository.existsById(1L)).thenReturn(true);

      this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/estudiantes/1"))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
   }

   @Test
   void testDelete_CuandoNoExiste_DeberiaRetornarNotFound() throws Exception {
      Mockito.when(this.repository.existsById(1L)).thenReturn(false);

      this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/estudiantes/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
   }
}