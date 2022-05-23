package com.tmax.cmp.repository;

import com.tmax.cmp.entity.common.account.AwsCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<AwsCredentials, Long> {


}
