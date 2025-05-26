package uniandes.edu.co.demo.repository;

import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import uniandes.edu.co.demo.modelo.ServicioMasSolicitado;

import java.util.*;

@Repository
public class CitaRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ServicioMasSolicitado> obtenerTopServiciosEntreFechas(Date desde, Date hasta) {
        List<Document> pipeline = Arrays.asList(
            new Document("$match", new Document("fecha_hora", new Document("$gte", desde).append("$lte", hasta))),
            new Document("$group", new Document("_id", "$id_servicio").append("total", new Document("$sum", 1))),
            new Document("$sort", new Document("total", -1)),
            new Document("$limit", 20)
        );

        AggregateIterable<Document> resultado = mongoTemplate.getCollection("Cita").aggregate(pipeline);

        List<ServicioMasSolicitado> lista = new ArrayList<>();
        for (Document doc : resultado) {
            int id = doc.getInteger("_id");
            int total = doc.getInteger("total");
            lista.add(new ServicioMasSolicitado(id, total));
        }

        return lista;
    }
}
