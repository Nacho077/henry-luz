package com.henry.luz.medidor.repository;

import com.henry.luz.medidor.model.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidorRepository extends JpaRepository<Medicion, Integer> {
}
