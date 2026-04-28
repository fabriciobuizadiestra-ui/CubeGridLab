package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.cubegridlab.entities.Cubesat;

public interface ICubesatRepository extends JpaRepository<Cubesat, Integer> {
}