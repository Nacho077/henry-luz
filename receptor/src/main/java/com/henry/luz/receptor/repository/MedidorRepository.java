package com.henry.luz.receptor.repository;

import com.henry.luz.receptor.model.Medidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidorRepository extends JpaRepository<Medidor, Integer> {
}
