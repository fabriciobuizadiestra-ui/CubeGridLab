package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Cubesats")
public class Cubesat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCubesat;

    @Column(length = 100, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    //getters y setters


    public int getIdCubesat() {
        return idCubesat;
    }

    public void setIdCubesat(int idCubesat) {
        this.idCubesat = idCubesat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
