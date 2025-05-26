package uniandes.edu.co.demo.controller;


import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Afiliado;
import uniandes.edu.co.demo.modelo.Parentesco;
import uniandes.edu.co.demo.repository.AfiliadoRepository;


@RestController
@RequestMapping("/afiliados")
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    // Crear un nuevo afiliado
    @PostMapping("/new/save")
    public ResponseEntity<String> crearAfiliado(@RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.save(afiliado);
            return new ResponseEntity<>("Afiliado creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear el afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un afiliado existente
    @PostMapping("/{numero_documento}/edit/save")
    public ResponseEntity<String> actualizarAfiliado(@PathVariable("numero_documento") int numero_documento, @RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.actualizarAfiliado(
                numero_documento,
                
                afiliado.getTipo_documento(),
                afiliado.getNombres(),
                afiliado.getApellidos(),
                afiliado.getFecha_nacimiento() != null ? afiliado.getFecha_nacimiento().toString() : null,
                afiliado.getDireccion(),
                afiliado.getTelefono(),
                afiliado.getTipo_afiliado(),
                afiliado.getParentesco() != null ? afiliado.getParentesco().toString() : null
            );
            return new ResponseEntity<>("Afiliado actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el Afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los afiliados
    @GetMapping("")
    public ResponseEntity<List<Afiliado>> obtenerTodosLosAfiliados() {
        try {
            List<Afiliado> afiliados = afiliadoRepository.buscarTodosLosAfiliados();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un afiliado por número de documento
    @GetMapping("/{numero_documento}")
    public ResponseEntity<List<Afiliado>> obtenerAfiliadoPorNumeroDocumento(@PathVariable("numero_documento") int numero_documento) {
        try {
            List<Afiliado> afiliados = afiliadoRepository.buscarAfiliadoPorNumeroDocumento(numero_documento);
            if (afiliados != null && !afiliados.isEmpty()) {
                return ResponseEntity.ok(afiliados);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar un afiliado por su número de documento
    @DeleteMapping("/{numero_documento}/delete")
    public ResponseEntity<String> eliminarAfiliado(@PathVariable("numero_documento") int numero_documento) {
        try {
            afiliadoRepository.eliminarAfiliadoPorNumeroDocumento(numero_documento);
            return new ResponseEntity<>("Afiliado eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el Afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // @Autowired
   // private BarRepositoryCustom barRepositoryCustom;

    // Obtener las bebidas más consumidas
    //@GetMapping("/mas-consumidas")
    //public ResponseEntity<List<Document>> obtenerBebidasMasConsumidas() {
      //  try {
        //    // Llamar al método en el repository que realiza la agregación
         //   List<Document> resultado = barRepositoryCustom.obtenerBebidasMasConsumidas();

            // Retornar el resultado de la consulta
         //   return ResponseEntity.ok(resultado);
      //  } catch (Exception e) {
      //      e.printStackTrace();
      //      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      //  }
  // }

    // Obtener el parentesco de un afiliado por su número de documento
    
}