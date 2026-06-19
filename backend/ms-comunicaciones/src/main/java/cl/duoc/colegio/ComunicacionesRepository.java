package cl.duoc.colegio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacionesRepository extends JpaRepository<Comunicaciones, Long> {
}