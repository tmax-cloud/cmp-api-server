package com.tmax.cmp.repository;

import com.tmax.cmp.entity.aws.ec2.Ec2Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AWSInstanceRepository extends JpaRepository<Ec2Instance, Long>{

    @Transactional
    @Modifying
    void deleteAllByInstanceIdNotIn(List<String> exist_instances);

    Ec2Instance findByInstanceIdAndRegion(String instanceId, String region);

    List<Ec2Instance> findByRegion(String region);

}
