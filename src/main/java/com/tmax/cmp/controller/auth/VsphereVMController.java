package com.tmax.cmp.controller.auth;

//import com.tmax.cmp.svc.auth.VsphereVMService;

import com.tmax.cmp.svc.vspheretest.VsphereVMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vsphere/v1/api/")
public class VsphereVMController {

    @Autowired
    VsphereVMService vsphereVMService;

    @GetMapping("/getvm")
    public void getToken(@RequestParam(name = "server", required = true) String server,
                         @RequestParam(name = "username", required = true) String username,
                         @RequestParam(name = "password", required = true) String password){


        vsphereVMService.getToken(server,username,password);
    }


    @GetMapping("/parsejson")
    public void jsontoString(@RequestParam(name= "json") String json) throws Exception{

        System.out.println("============controller json====================");
        System.out.println(json);
        vsphereVMService.parseJsonToObject(json);
    }
}
