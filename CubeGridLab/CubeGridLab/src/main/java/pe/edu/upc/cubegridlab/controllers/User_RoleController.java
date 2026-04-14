package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.cubegridlab.dtos.User_RoleDTO;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-roles")
public class User_RoleController {
    @Autowired
    private IUser_RoleService urS;

    @GetMapping
    public ResponseEntity<List<User_RoleDTO>> listar() {
        ModelMapper m = new ModelMapper();
        List<User_RoleDTO> listaUserRoles = urS.list().stream()
                .map(y -> m.map(y, User_RoleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUserRoles);
    }
}

