package uniandes.edu.co.demo.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;
import java.util.Date; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam; 

import uniandes.edu.co.demo.modelo.Cita;
import uniandes.edu.co.demo.modelo.Disponibilidad;
import uniandes.edu.co.demo.modelo.IPS;
import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.modelo.ServicioMasSolicitado;
import uniandes.edu.co.demo.repository.CitaRepository;
import uniandes.edu.co.demo.repository.CitaRepositoryCustom;
import uniandes.edu.co.demo.repository.ServicioRepository;

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

    @Autowired
    private CitaRepositoryCustom citaRepositorycustom;

    @GetMapping("/top-servicios")
    public ResponseEntity<List<ServicioMasSolicitado>> obtenerTopServicios(
        @RequestParam("desde") String desdeStr,
        @RequestParam("hasta") String hastaStr
    ) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date desde = sdf.parse(desdeStr);
            Date hasta = sdf.parse(hastaStr);

            List<ServicioMasSolicitado> resultado = citaRepositorycustom.obtenerTopServiciosEntreFechas(desde, hasta);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    //consukar disponibilidad de un servicio en una fecha
    @GetMapping("/disponibilidad/{id_servicio}")
    public List<Cita> consultarDisponibilidad(@PathVariable int id_servicio) {
    Date hoy = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(hoy);
    calendar.add(Calendar.WEEK_OF_YEAR, 4);
    Date enCuatroSemanas = calendar.getTime();

    return citaRepository.buscarPorServicioYFecha(id_servicio, hoy, enCuatroSemanas);
    }

    @Autowired
    private ServicioRepository servicioRepository;


    //diponibilidad libre
    @GetMapping("/{id}/disponibilidad/disponible")
public ResponseEntity<List<Disponibilidad>> obtenerDisponibilidadLibreDesdeFecha(
    @PathVariable int id,
    @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio) {

    List<Servicio> servicios = servicioRepository.findDisponibilidadDisponibleByServicioId(id);
    if (servicios.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    List<Disponibilidad> disponibles = servicios.get(0).getDisponibilidad();
    if (disponibles == null || disponibles.isEmpty()) {
        return ResponseEntity.noContent().build();
    }

    // Calcular fecha final (4 semanas después)
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fechaInicio);
    calendar.add(Calendar.WEEK_OF_YEAR, 4);
    Date fechaFin = calendar.getTime();

    // Filtrar disponibilidades en el rango
    List<Disponibilidad> disponiblesEnRango = disponibles.stream()
        .filter(d -> d.getHorario_inicio() != null &&
                     !d.getHorario_inicio().before(fechaInicio) &&
                     !d.getHorario_inicio().after(fechaFin))
        .collect(Collectors.toList());

    if (disponiblesEnRango.isEmpty()) {
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(disponiblesEnRango);
}


}
