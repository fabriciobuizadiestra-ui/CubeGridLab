package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailUser(String emailUser);
}
