package cl.duoc.colegio;

public class Comunicacion {
    private Long id;
    private String titulo;
    private String contenido;

    public Comunicacion(Long id, String titulo, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // Getters necesarios para que Spring Boot convierta esto a JSON
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
}