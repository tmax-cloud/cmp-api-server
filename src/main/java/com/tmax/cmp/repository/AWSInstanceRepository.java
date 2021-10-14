package com.tmax.cmp.repository;

import com.tmax.cmp.dto.AWSInstanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AWSInstanceRepository extends JpaRepository<AWSInstanceDTO, Long>{

    @Query(value = "delete * from awsinstance where not instance_id in (:exist_instances)", nativeQuery = true)
    void deleteExcept(@Param("exist_instances") String exist_instances);

}
