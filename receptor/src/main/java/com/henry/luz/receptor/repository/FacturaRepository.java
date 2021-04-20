package com.henry.luz.receptor.repository;

import com.henry.luz.receptor.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    @Query
    List<Factura> findAllByClienteId(Integer id);
}
