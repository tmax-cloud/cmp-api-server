package com.tmax.cmp.controller.common;

import com.tmax.cmp.svc.common.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credentials")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @PostMapping("/newCredential/aws")
    public void addNewAwsCredentials(@RequestParam(name = "accessKey", required = true) String accessKey,
                                  @RequestParam(name = "secretKey", required = true) String secretKey){

        credentialService.saveAwsCredential(accessKey,secretKey);

    }
}
