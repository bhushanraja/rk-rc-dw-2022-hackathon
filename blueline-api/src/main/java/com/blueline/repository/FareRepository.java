package com.blueline.repository;

import java.util.List;

import com.blueline.entity.FareEstimationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FareRepository extends CrudRepository<FareEstimationEntity, Integer>{
	@Query("select f.fare from FareEstimationEntity f where f.source=:source and f.destination=:destination")
	Float findFareBySourceAndDestination(@Param("source") String source,@Param("destination") String destination);
	
	@Query("SELECT f FROM FareEstimationEntity f ")
	List<FareEstimationEntity> findFares();
}
