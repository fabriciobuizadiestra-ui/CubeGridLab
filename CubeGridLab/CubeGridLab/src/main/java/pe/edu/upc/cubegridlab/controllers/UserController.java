package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.UserDTO;
import pe.edu.upc.cubegridlab.dtos.UserInsertDTO;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
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
    @PostMapping("/registra")
    public ResponseEntity<?> registrar(@RequestBody UserInsertDTO dto){
        // Validaciones de entrada
        if (dto == null || dto.getNameUser() == null || dto.getNameUser().isEmpty() ||
            dto.getLastNameUser() == null || dto.getLastNameUser().isEmpty() ||
            dto.getEmailUser() == null || dto.getEmailUser().isEmpty() ||
            dto.getPasswordUser() == null || dto.getPasswordUser().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Campos obligatorios faltantes");
        }

        try {
            ModelMapper m = new ModelMapper();
            User u = m.map(dto, User.class);
            User usuario = uS.insert(u);
            UserDTO responseDTO = m.map(usuario, UserDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<User> usuario = uS.listId(id);

        if (usuario.isPresent()) {
            UserDTO dto = m.map(usuario.get(), UserDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody UserInsertDTO dto) {
        // Validación de entrada
        if (dto == null || dto.getIdUser() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID de usuario inválido");
        }

        try {
            Optional<User> existente = uS.listId(dto.getIdUser());
            if (existente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }

            User u = existente.get();

            if (dto.getNameUser() != null && !dto.getNameUser().isEmpty()) {
                u.setNameUser(dto.getNameUser());
            }
            if (dto.getLastNameUser() != null && !dto.getLastNameUser().isEmpty()) {
                u.setLastNameUser(dto.getLastNameUser());
            }
            if (dto.getEmailUser() != null && !dto.getEmailUser().isEmpty()) {
                u.setEmailUser(dto.getEmailUser());
            }
            if (dto.getPasswordUser() != null && !dto.getPasswordUser().isEmpty()) {
                u.setPasswordUser(dto.getPasswordUser());
            }
            if (dto.getRegisterDateUser() != null) {
                u.setRegisterDateUser(dto.getRegisterDateUser());
            }
            if (dto.getStatusUser() != null && !dto.getStatusUser().isEmpty()) {
                u.setStatusUser(dto.getStatusUser());
            }

            uS.update(u);

            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar usuario: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        // Validación de entrada
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID de usuario inválido");
        }

        try {
            Optional<User> usuario = uS.listId(id);

            if (usuario.isPresent()) {
                uS.delete(id);
                return ResponseEntity.ok("Usuario eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
