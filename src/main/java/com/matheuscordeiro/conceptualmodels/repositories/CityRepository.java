package com.matheuscordeiro.conceptualmodels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.matheuscordeiro.conceptualmodels.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM City obj WHERE obj.city.id = :stateId ORDER BY obj.name")
	public List<City> findCities(@Param("estadoId") Integer state_id);
}
