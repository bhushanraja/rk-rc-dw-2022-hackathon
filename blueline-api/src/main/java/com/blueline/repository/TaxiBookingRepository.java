package com.blueline.repository;

import java.util.List;

import com.blueline.entity.TaxiBookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaxiBookingRepository extends CrudRepository<TaxiBookingEntity, Integer>{
	List<TaxiBookingEntity> findByUserMobile(Long userMobile);
}
