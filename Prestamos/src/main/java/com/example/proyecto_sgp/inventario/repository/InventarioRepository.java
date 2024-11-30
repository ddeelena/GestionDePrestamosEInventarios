package com.example.proyecto_sgp.inventario.repository;

import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends MongoRepository<ElementosDti, String> {

}
