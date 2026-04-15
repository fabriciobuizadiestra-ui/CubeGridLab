package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.repositories.IUserRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.util.List;

@Service
public class UserServiceImplement implements IUserService{
    @Autowired
    private IUserRepository uR;

    @Override
    public List<User> list() {
        return uR.findAll();
    }
}
