package com.tmax.cmp.controller.terraform;

import com.tmax.cmp.svc.terraform.TerraformResourceCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@EnableAsync
@RestController
@RequestMapping("/api/v1/terraform/resource")
public class TerraformResourceCRUDController {
    @Autowired
    private TerraformResourceCRUDService terraformResourceCRUDService;

    @PostMapping("/create")
    public void createInfraResource(
            @RequestParam(value = "path") String workingDirPath,
            @RequestParam(value = "vendor") String vendorName,
            @RequestParam(value = "resource") String resourceName,
            @RequestParam(value = "region") String region
        ) throws IOException, SQLException {
        terraformResourceCRUDService.createResource(workingDirPath, vendorName, resourceName, region);
    }

    @DeleteMapping("/delete")
    public void deleteInfraResource(
            @RequestParam(value = "path") String workingDirPath,
            @RequestParam(value = "vendor") String vendorName,
            @RequestParam(value = "resource") String resourceName,
            @RequestParam(value = "region") String region
        ) throws IOException, SQLException {
        terraformResourceCRUDService.deleteResource(workingDirPath, vendorName, resourceName, region);
    }

}
