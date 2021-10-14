package com.tmax.cmp.repository;

import com.tmax.cmp.dto.AWSInstanceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AWSInstanceRepository extends JpaRepository<AWSInstanceDTO, Long>{

    @Transactional
    @Modifying
    void deleteAllByInstanceIdNotIn(List<String> exist_instances);
}
