package com.tmax.cmp.svc.common;

import com.tmax.cmp.entity.common.account.AwsCredentials;
import com.tmax.cmp.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    CredentialRepository credentialRepository;

    public void saveAwsCredential(String accessKey, String secretKey){

        AwsCredentials awsCredentials = AwsCredentials.builder()
                .accessKey(accessKey)
                .secretKey(secretKey)
                .build();

        credentialRepository.save(awsCredentials);
    }

    public List<AwsCredentials> getAwsCredentials(){
        return credentialRepository.findAll();
    }

}
