package com.tmax.cmp.repository;

import com.tmax.cmp.entity.common.account.VsphereCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VsphereCredentialRepository extends JpaRepository<VsphereCredentials,Long> {
}
