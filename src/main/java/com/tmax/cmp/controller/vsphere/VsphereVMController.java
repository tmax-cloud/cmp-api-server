package com.tmax.cmp.controller.vsphere;

//import com.tmax.cmp.svc.auth.VsphereVMService;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.common.client.vSphereClient;
import com.tmax.cmp.svc.vsphere.AuthUtils;
import com.tmax.cmp.svc.vsphere.VsphereVMService;
import com.vmware.vcenter.VM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vsphere/vm")
public class VsphereVMController {

    @Autowired
    VsphereVMService vsphereVMService;

    @Autowired
    AuthUtils authUtils;

    @GetMapping("/getVmListFromServer")
    public void getToken(@RequestParam(name = "server", required = true) String server,
                         @RequestParam(name = "username", required = true) String username,
                         @RequestParam(name = "password", required = true) String password){

        try{
            authUtils.makeVMClient(server,username,password);
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    @GetMapping("/parsejson")
    public void jsontoString(@RequestParam(name= "json") String json) throws Exception{

        System.out.println("============controller json====================");
        System.out.println(json);
        vsphereVMService.parseJsonToObject(json);
    }

    @GetMapping("/syncVm")
    public void syncVm(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<vSphereClient> vsphereClientList = (List<vSphereClient>)context.getBean("vSphereClients");

        for(vSphereClient vSphereClient : vsphereClientList){
            VM vmClient = vSphereClient.getVmClient();
            try{
                vsphereVMService.syncVMFromServerToDB(vmClient);
            }catch (Exception e){

            }
        }

    }

}
