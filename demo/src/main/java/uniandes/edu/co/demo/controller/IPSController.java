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

import uniandes.edu.co.demo.modelo.IPS;
import uniandes.edu.co.demo.repository.IPSRepository;

@RestController
@RequestMapping("/ips")
public class IPSController {

    @Autowired
    private IPSRepository ipsRepository;

    //crear uan nueva IPS
    @PostMapping("/new/save")
    public ResponseEntity<String> crearIps(@RequestBody IPS ips) {
        try {
            ipsRepository.save(ips);
            return new ResponseEntity<>("IPS creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar una IPS existente
    @PostMapping("/{NIT}/edit/save")
    public ResponseEntity<String> actualizarIps(@PathVariable("NIT") String NIT, @RequestBody IPS ips) {
        try {
            ipsRepository.actualizarIPS(NIT, ips.getNombre(), ips.getDireccion(), ips.getTelefono());
            return new ResponseEntity<>("IPS actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //obtener todas las IPS
    @GetMapping("")
    public ResponseEntity<List<IPS>> obtenerTodasLasIPS() {
        try {
            List<IPS> ipsList = ipsRepository.BuscarTodasLasIPS();
            return ResponseEntity.ok(ipsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Consultar IPS por NIT
    @GetMapping("/{NIT}")
    public ResponseEntity<List<IPS>> obtenerIPSPorNIT(@PathVariable("NIT") String NIT) {
        try {
            List<IPS> ipsList = ipsRepository.BuscarIPSPorNIT(NIT);
            if (ipsList != null && !ipsList.isEmpty()) {
                return ResponseEntity.ok(ipsList);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Eliminar IPS por NIT
    @DeleteMapping("/{NIT}/delete")
    public ResponseEntity<String> eliminarIps(@PathVariable("NIT") String NIT) {
        try {
            ipsRepository.eliminarIPSPorNIT(NIT);
            return new ResponseEntity<>("IPS eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
