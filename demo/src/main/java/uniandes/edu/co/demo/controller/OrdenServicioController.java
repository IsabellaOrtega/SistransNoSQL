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

import uniandes.edu.co.demo.modelo.OrdenServicio;
import uniandes.edu.co.demo.repository.OrdenServicioRepository;

@RestController
@RequestMapping("ordenes-servicio")
public class OrdenServicioController {
    @Autowired
    private OrdenServicioRepository ordenServicioRepository;

    //crear una nueva orden de servicio
    @PostMapping("/new/save")
    public ResponseEntity<String> crearOrdenServicio(@RequestBody OrdenServicio ordenServicio) {
        try {
            ordenServicioRepository.save(ordenServicio);
            return new ResponseEntity<>("Orden de servicio creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Actualizar una orden de servicio existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarOrdenServicio(@PathVariable("id") int id, @RequestBody OrdenServicio ordenServicio) {
        try {
            ordenServicioRepository.actualizarOrden(
                id,
                ordenServicio.getFecha(),
                ordenServicio.getEstado(),
                ordenServicio.getId_servicio(),
                ordenServicio.getNumero_documento_afiliado(),
                ordenServicio.getNumero_registro_medico()
            );
            return new ResponseEntity<>("Orden de servicio actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //obtener todas las ordenes de servicio
    @GetMapping("")
    public ResponseEntity<List<OrdenServicio>> obtenerTodasLasOrdenesServicio() {
        try {
            List<OrdenServicio> ordenes = ordenServicioRepository.buscarTodasLasOrdenes();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //obtener una orden de servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<List<OrdenServicio>> obtenerOrdenServicioPorId(@PathVariable("id") int id) {
        try {
            List<OrdenServicio> ordenes = ordenServicioRepository.buscarOrdenPorId(id);
            if (ordenes != null && ordenes.isEmpty()) {
                return ResponseEntity.ok(ordenes);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Eliminar una orden de servicio por ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarOrdenServicio(@PathVariable("id") int id) {
        try {
            ordenServicioRepository.eliminarOrdenPorId(id);
            return new ResponseEntity<>("Orden de servicio eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
