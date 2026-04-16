package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

public class Misiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMision;
    private String nombreMision;
    private String descripcionMision;
    @ManyToOne
    @JoinColumn(name = "idEvalucion")
    private Evaluaciones evaluaciones;
//    falta agregarle el id cubesat
}
