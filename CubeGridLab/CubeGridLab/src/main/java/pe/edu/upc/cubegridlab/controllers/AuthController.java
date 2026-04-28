package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.LoginDTO;
import pe.edu.upc.cubegridlab.dtos.UserDTO;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService uS;

    @Autowired
    private IUser_RoleService urS;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

        if (dto.getEmailUser() == null || dto.getEmailUser().isEmpty()
                || dto.getPasswordUser() == null || dto.getPasswordUser().isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Correo y contraseña son obligatorios");
        }

        Optional<User> usuario = uS.findByEmail(dto.getEmailUser());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        User user = usuario.get();

        if (!user.getPasswordUser().equals(dto.getPasswordUser())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Contraseña incorrecta");
        }

        UserDTO responseDTO = new UserDTO();
        responseDTO.setNameUser(user.getNameUser());
        responseDTO.setEmailUser(user.getEmailUser());

        Optional<User_Role> userRole = urS.findByUserId(user.getIdUser());
        if (userRole.isPresent()) {
            responseDTO.setRoleUser(
                    userRole.get().getRole().getNameRole()
            );
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login exitoso");
        response.put("user", responseDTO);

        return ResponseEntity.ok(response);
    }
}