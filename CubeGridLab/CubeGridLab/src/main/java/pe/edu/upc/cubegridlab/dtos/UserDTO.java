package pe.edu.upc.cubegridlab.dtos;


import java.time.LocalDate;

public class UserDTO {
    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private LocalDate registerDateUser;
    private String statusUser;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public LocalDate getRegisterDateUser() {
        return registerDateUser;
    }

    public void setRegisterDateUser(LocalDate registerDateUser) {
        this.registerDateUser = registerDateUser;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }
}
