package uniandes.edu.co.demo.modelo;

public class ServicioMasSolicitado {

    private int _id;   // id_servicio agrupado por Mongo
    private int total;

    public ServicioMasSolicitado() {
    }

    public ServicioMasSolicitado(int _id, int total) {
        this._id = _id;
        this.total = total;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
