package co.edu.cue.inventario.repository;

import co.edu.cue.inventario.ElementosDti.ElementosDti;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends MongoRepository<ElementosDti, String> {

}
