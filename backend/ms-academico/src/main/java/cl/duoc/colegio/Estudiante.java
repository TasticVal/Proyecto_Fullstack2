package cl.duoc.colegio;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String rut;

    // CONSTRUCTOR VACÍO (Obligatorio para JPA)
    public Estudiante() {}

    public Estudiante(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
}