package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.repositories.IUser_RoleRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.List;

@Service
public class User_RoleServiceImplement implements IUser_RoleService {
    @Autowired
    private IUser_RoleRepository urR;

    @Override
    public List<User_Role> list() {
        return urR.findAll();
    }
}

