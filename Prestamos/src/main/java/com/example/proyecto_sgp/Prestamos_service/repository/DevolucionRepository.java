package com.example.proyecto_sgp.Prestamos_service.repository;

import com.example.proyecto_sgp.Prestamos_service.entity.Devolucion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionRepository extends MongoRepository<Devolucion, String> {
}
