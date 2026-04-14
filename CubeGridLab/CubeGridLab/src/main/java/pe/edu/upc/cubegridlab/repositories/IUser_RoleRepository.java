package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.User_Role;

@Repository
public interface IUser_RoleRepository extends JpaRepository<User_Role, Integer> {
}

