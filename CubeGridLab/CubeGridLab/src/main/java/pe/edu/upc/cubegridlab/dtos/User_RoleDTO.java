package pe.edu.upc.cubegridlab.dtos;

import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.Role;

public class User_RoleDTO {
    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

