package com.tmax.cmp.svc.common;

import com.tmax.cmp.entity.common.account.AwsCredentials;
import com.tmax.cmp.entity.common.account.VsphereCredentials;
import com.tmax.cmp.repository.AwsCredentialRepository;
import com.tmax.cmp.repository.VsphereCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    AwsCredentialRepository awsCredentialRepository;

    @Autowired
    VsphereCredentialRepository vsphereCredentialRepository;

    public void saveAwsCredential(String accessKey, String secretKey){

        AwsCredentials awsCredentials = AwsCredentials.builder()
                .accessKey(accessKey)
                .secretKey(secretKey)
                .build();

        awsCredentialRepository.save(awsCredentials);
    }

    public List<AwsCredentials> getAwsCredentials(){
        return awsCredentialRepository.findAll();
    }

    public List<VsphereCredentials> getVsphereCredentials(){
        return vsphereCredentialRepository.findAll();
    }

}
