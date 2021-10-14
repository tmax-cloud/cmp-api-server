package com.tmax.cmp.repository;

import com.tmax.cmp.dto.AWSInstanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AWSInstanceRepository extends JpaRepository<AWSInstanceDTO, Long>{

    @Transactional
    @Modifying
    @Query("delete from AWSInstanceDTO where not instanceId in (:exist_instances)")
    void deleteExcept(@Param("exist_instances") String exist_instances);

}
