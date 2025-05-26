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

import uniandes.edu.co.demo.modelo.Medico;
import uniandes.edu.co.demo.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    //CRear un nuevo médico
    @PostMapping("/new/save")
    public ResponseEntity<String> crearMedico(@RequestBody Medico medico) {
        try {
            medicoRepository.save(medico);
            return new ResponseEntity<>("Médico creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el médico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un médico existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarMedico(@PathVariable("id") int id, @RequestBody Medico medico) {
        try {
            medicoRepository.actualizarMedico(
                id,
                medico.getNombres(),
                medico.getApellidos(),
                medico.getTipos_documento(),
                medico.getNumero_documento(),
                medico.getEspecialidad()
            );
            return new ResponseEntity<>("Médico actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el médico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //obetener todos los médicos
    @GetMapping("")
    public ResponseEntity<List<Medico>> obtenerTodosLosMedicos() {
        try {
            List<Medico> medicos = medicoRepository.buscarTodosLosMedicos();
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Consultar médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<List<Medico>> obtenerMedicoPorId(@PathVariable("id") int id) {
        try {
            List<Medico> medicos = medicoRepository.buscarMedicoPorDocumento(id);
            if (medicos != null && medicos.isEmpty()) {
                return ResponseEntity.ok(medicos);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar médico por ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarMedico(@PathVariable("id") int id) {
        try {
            medicoRepository.eliminarMedicoPorDocumento(id);
            return new ResponseEntity<>("Médico eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el médico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
