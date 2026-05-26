package cl.duoc.colegio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // JpaRepository ya trae métodos como findAll(), save(), etc.
}