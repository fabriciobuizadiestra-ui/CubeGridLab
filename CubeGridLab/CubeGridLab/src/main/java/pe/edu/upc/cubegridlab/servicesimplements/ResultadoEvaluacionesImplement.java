package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Misiones;
import pe.edu.upc.cubegridlab.entities.ResultadoEvaluacion;
import pe.edu.upc.cubegridlab.repositories.IResultadoEvaluacionRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IMisionesService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IResultadoEvaliacionesService;

import java.util.List;

@Service
public class ResultadoEvaluacionesImplement implements IResultadoEvaliacionesService {
    @Autowired
    private IResultadoEvaluacionRepository reR;
    @Override
    public List<ResultadoEvaluacion> list() {return reR.findAll();}
}
