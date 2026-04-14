package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.cubegridlab.dtos.UserDTO;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private IUserService uS;
    @GetMapping
    public ResponseEntity<List<UserDTO>> listar()
    {
        ModelMapper m = new ModelMapper();
        List<UserDTO> listaUsuarios= uS.list().stream()
                .map(y -> m.map(y,UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsuarios);
    }
}
