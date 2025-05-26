package uniandes.edu.co.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.demo.modelo.Cita;
import uniandes.edu.co.demo.modelo.IPS;
import uniandes.edu.co.demo.repository.CitaRepository;

@RestController
@RequestMapping("/citas")
public class CitaController {
    
    @Autowired
    private CitaRepository citaRepository;

    //crear una nueva cita
    @PostMapping("/new/save")
    public ResponseEntity<String> crearCita(@RequestBody Cita cita) {
        try {
            citaRepository.save(cita);
            return new ResponseEntity<>("Cita creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la cita: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una cita existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCita(@PathVariable("id") int id, @RequestBody Cita cita) {
        try {
            citaRepository.actualizarCita(id, cita.getId_servicio(), cita.getNumero_documento_afiliado(), cita.getFecha_hora(), cita.getId_orden_servicio());
            return new ResponseEntity<>("IPS actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //obtener todas las citas
    @GetMapping("")
    public ResponseEntity<List<Cita>> obtenerTodasLasCitas() {
        try {
            List<Cita> citas = citaRepository.buscarTodasLasCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Consultar citas por id
    @GetMapping("/{id}")
    public ResponseEntity<List<Cita>> obtenerCitaPorId(@PathVariable("id") int id) {
        try {
            List<Cita> citas = citaRepository.buscarCitaPorId(id);
            if (citas != null && !citas.isEmpty()) {
                return ResponseEntity.ok(citas);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Eliminar CIta por id
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCita(@PathVariable("id") int id) {
        try {
            citaRepository.eliminarCitaPorId(id);
            return new ResponseEntity<>("IPS eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
