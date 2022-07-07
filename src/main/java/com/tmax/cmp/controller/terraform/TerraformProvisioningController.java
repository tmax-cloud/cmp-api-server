package com.tmax.cmp.controller.terraform;

import com.tmax.cmp.svc.terraform.TerraformProvisioningService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@EnableAsync
@RestController
@RequestMapping("/api/v1/terraform/resource")
public class TerraformProvisioningController {
    TerraformProvisioningService terraformProvisioningService = new TerraformProvisioningService();

    @GetMapping("/create")
    public void createInfraResource(@RequestParam String workingDirPath) throws IOException, InterruptedException, NoSuchFieldException {
        terraformProvisioningService.createResource(workingDirPath);
    }

}
