package com.tmax.cmp.repository;

import com.tmax.cmp.entity.terraform.TerraformResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerraformResultRepository extends JpaRepository<TerraformResult, Long> {
}
