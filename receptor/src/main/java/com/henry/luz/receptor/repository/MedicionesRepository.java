package com.henry.luz.receptor.repository;

import com.henry.luz.receptor.model.Mediciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicionesRepository extends JpaRepository<Mediciones, Integer> {
    @Query
    List<Mediciones> findByMedidorId(Integer id);

    @Query//("SELECT * as m FROM Mediciones WHERE m.fecha BETWEEN :dateI AND :dateF")
    List<Mediciones> findAllByMedidorIdAndFechaBetween(Integer id, Date dateI, Date dateF);

}
