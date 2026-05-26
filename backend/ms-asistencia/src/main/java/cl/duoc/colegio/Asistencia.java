package cl.duoc.colegio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column; // 1. IMPORTANTE: Importar Column

@Entity
@Table(name = "asistencias") // 2. CORREGIDO: Coincide con tu SQL (plural)
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 3. CORREGIDO: Mapeamos explícitamente el nombre de la columna en SQL
    @Column(name = "estudiante_id") 
    private Long idEstudiante;
    
    private String fecha;
    private String estado;

    // Constructor vacío obligatorio para JPA
    public Asistencia() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}