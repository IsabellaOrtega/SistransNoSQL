package uniandes.edu.co.demo.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.ServicioRepository;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    
    @Autowired
    private ServicioRepository servicioRepository;

    //crear un nuevo servicio
    @PostMapping("/new/save")
    public ResponseEntity<String> crearServicio(@RequestBody Servicio servicio) {
        try {
            servicioRepository.save(servicio);
            return new ResponseEntity<>("Servicio creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un servicio existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarServicio(@PathVariable("id") int id, @RequestBody Servicio servicio) {
        try {
            servicioRepository.actualizarServicio(
                id,
                servicio.getNombre(),
                servicio.getTipo_servicio(),
                servicio.isRequiere_orden(),
                servicio.getNit_ips(),
                servicio.getDisponibilidad()
            );
            return new ResponseEntity<>("Servicio actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Consultar todos los servicios
    @GetMapping("")
    public ResponseEntity<List<Servicio>> obtenerTodosLosServicios() {
        try {
            List<Servicio> servicios = servicioRepository.buscarTodosLosServicios();
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Consultar servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<List<Servicio>> obtenerServicioPorId(@PathVariable("id") int id) {
        try {
            List<Servicio> servicio = servicioRepository.buscarServicioPorId(id);
            if (servicio != null && !servicio.isEmpty()) {
                return ResponseEntity.ok(servicio);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ELiminar un bar por ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarServicio(@PathVariable("id") int id) {
        try {
            servicioRepository.eliminarServicioPorId(id);
            return new ResponseEntity<>("Servicio eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@Autowired
    //falta hacer la parte embebida
}
