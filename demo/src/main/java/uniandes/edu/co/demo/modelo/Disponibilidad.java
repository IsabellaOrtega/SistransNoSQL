package uniandes.edu.co.demo.modelo;

import java.util.Date;

import lombok.ToString;

@ToString
public class Disponibilidad {
    private Date horario_inicio;
    private Date horario_fin;
    private String estado;
    private boolean asistencia;

    public Disponibilidad(Date horario_inicio, Date horario_fin, String estado, boolean asistencia) {
        this.horario_inicio = horario_inicio;
        this.horario_fin = horario_fin;
        this.estado = estado;
        this.asistencia = asistencia;
    }
    public Date getHorario_inicio() {
        return horario_inicio;
    }
    public void setHorario_inicio(Date horario_inicio) {
        this.horario_inicio = horario_inicio;
    }
    public Date getHorario_fin() {
        return horario_fin;
    }
    public void setHorario_fin(Date horario_fin) {
        this.horario_fin = horario_fin;
    }
    public String getEstado_cita() {
        return estado;
    }
    public void setEstado_cita(String estado) {
        this.estado = estado;
    }
    public boolean isAsistencia() {
        return asistencia;
    }
    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

}
