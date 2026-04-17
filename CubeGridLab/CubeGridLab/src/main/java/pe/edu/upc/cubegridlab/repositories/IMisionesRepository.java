package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Misiones;

@Repository
public interface IMisionesRepository extends JpaRepository<Misiones, Integer> {
}
