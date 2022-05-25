package com.tmax.cmp.repository;

import com.tmax.cmp.entity.common.account.AwsCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<AwsCredentials, Long> {

}
