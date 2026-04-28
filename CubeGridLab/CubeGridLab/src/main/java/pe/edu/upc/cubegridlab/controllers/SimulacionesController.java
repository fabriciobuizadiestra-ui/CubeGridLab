package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.entities.Simulaciones;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISimulacionesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulaciones")
public class SimulacionesController {

    @Autowired
    private ISimulacionesService sS;

    @GetMapping
    public List<Simulaciones> listar() {
        return sS.list();
    }

    @PostMapping("/iniciar") //US16: Ejecutar simulación de misión
    public ResponseEntity<?> iniciarSimulacion(@RequestBody Simulaciones s) {

        s.setEstado("COMPLETADA");
        s.setResultado("Órbita estable alcanzada correctamente");

        return ResponseEntity.ok(sS.insert(s));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isPresent()) {
            return ResponseEntity.ok(simulacion.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        sS.delete(id);
        return ResponseEntity.ok("Simulación eliminada");
    }

    @GetMapping("/historial") //US02: Como usuario, quiero revisar mis simulaciones anteriores
    public ResponseEntity<?> historialSimulaciones() {
        return ResponseEntity.ok(sS.list());
    }

    @GetMapping("/{id}/resultado") //US05: Como usuario, quiero ver si una simulación falló
    public ResponseEntity<?> verResultado(@PathVariable int id) {

        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(simulacion.get().getResultado());
    }

    @GetMapping("/{id}/informe") //US18: Como usuario, quiero obtener informe posterior a la misión
    public ResponseEntity<?> informeMision(@PathVariable int id) {

        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Simulaciones s = simulacion.get();

        String informe = "Simulación: " + s.getNombre()
                + " | Estado: " + s.getEstado()
                + " | Resultado: " + s.getResultado()
                + " | Tipo: " + s.getTipo();

        return ResponseEntity.ok(informe);
    }

    @GetMapping("/{id}/reporte") //US03: Como estudiante, quiero generar un reporte PDF
    public ResponseEntity<?> generarReporte(@PathVariable int id) {

        Optional<Simulaciones> simulacion = sS.listId(id);

        if (simulacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Simulaciones s = simulacion.get();

        String reporte = "=== REPORTE DE SIMULACIÓN ===\n"
                + "Nombre: " + s.getNombre() + "\n"
                + "Estado: " + s.getEstado() + "\n"
                + "Resultado: " + s.getResultado() + "\n"
                + "Altitud: " + s.getAltitud() + "\n"
                + "Velocidad: " + s.getVelocidad();

        return ResponseEntity.ok(reporte);
    }

}