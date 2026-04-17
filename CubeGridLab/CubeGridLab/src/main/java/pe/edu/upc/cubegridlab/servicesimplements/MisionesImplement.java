package pe.edu.upc.cubegridlab.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Misiones;
import pe.edu.upc.cubegridlab.repositories.IMisionesRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IMisionesService;

import java.util.List;

@Service
public class MisionesImplement implements IMisionesService {
    @Autowired
    private IMisionesRepository miR;
    @Override
    public List<Misiones> list() {return miR.findAll();}
}
