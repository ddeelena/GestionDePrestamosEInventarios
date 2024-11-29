package com.example.proyecto_sgp.Prestamos_service.repository;

import com.example.proyecto_sgp.Prestamos_service.entity.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends MongoRepository<Prestamo, String> {
    
}