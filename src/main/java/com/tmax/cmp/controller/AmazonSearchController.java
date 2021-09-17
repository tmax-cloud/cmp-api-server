package com.tmax.cmp.controller;

import com.tmax.cmp.dto.AmazonDTO;
import com.tmax.cmp.svc.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/amazonsearch")

public class AmazonSearchController {
    @Autowired
    private AmazonService amazonService;

    @GetMapping("/instance")
    public AmazonDTO get(@RequestParam(name = "query") String query, @RequestParam(name = "region", required = false) String region){
        return amazonService.getInstance(query, region);
    }

    @GetMapping("/instances")
    public List<AmazonDTO> list(@RequestParam(name = "region", required = false) String region) {
        return amazonService.getAllInstances(region);
    }
}
