package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignDTO;

import java.util.List;
import java.util.Optional;

public interface IUser_RoleService {
    List<User_Role> list();
    Optional<User_Role> findByUserId(int idUser);
    public User_Role assignRoleToUser(User_RoleAssignDTO assignDTO);
}

