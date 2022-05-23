package com.tmax.cmp.controller.common;

import com.tmax.cmp.svc.common.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credentials")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @GetMapping("/newCredential/aws")
    public void addNewAwsCredentials(@RequestParam(name = "accessKey", required = true) String accessKey,
                                  @RequestParam(name = "secretKey", required = true) String secretKey){

        credentialService.saveAwsCredential(accessKey,secretKey);

    }
}
