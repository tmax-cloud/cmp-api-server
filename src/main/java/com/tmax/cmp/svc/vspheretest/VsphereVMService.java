package com.tmax.cmp.svc.vspheretest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cmp.entity.vsphere.vm.vmresources.VsphereVM;
import com.tmax.cmp.repository.VsphereVMRepository;
import com.vmware.vapi.bindings.StubConfiguration;
import com.vmware.vapi.bindings.StubFactory;
import com.vmware.vcenter.VM;
import com.vmware.vcenter.VMTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;

@Service
public class VsphereVMService {

    @Autowired
    VsphereVMRepository vsphereVMRepository;

    public void getVM(StubFactory stubFactory, StubConfiguration sessionStubConfig,
                      String vmName){

        VM vmservice = stubFactory.createStub(VM.class, sessionStubConfig);

        VMTypes.FilterSpec vmFilterSpec = new VMTypes.FilterSpec
                .Builder().setNames(Collections.singleton(vmName)).build();

        VMTypes.Info vmList = vmservice.get(vmName);


        System.out.println(vmList);



    }

    public void parseJsonToObject(String jsonData) throws JsonProcessingException {

        jsonData = "{\n" +
                "    \"instant_clone_frozen\": false,\n" +
                "    \"cdroms\": {\n" +
                "        \"3000\": {\n" +
                "            \"start_connected\": false,\n" +
                "            \"backing\": {\n" +
                "                \"device_access_type\": \"PASSTHRU\",\n" +
                "                \"type\": \"CLIENT_DEVICE\"\n" +
                "            },\n" +
                "            \"allow_guest_control\": true,\n" +
                "            \"ide\": {\n" +
                "                \"master\": true,\n" +
                "                \"primary\": true\n" +
                "            },\n" +
                "            \"label\": \"CD/DVD drive 1\",\n" +
                "            \"state\": \"NOT_CONNECTED\",\n" +
                "            \"type\": \"IDE\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"memory\": {\n" +
                "        \"hot_add_increment_size_MiB\": 0,\n" +
                "        \"size_MiB\": 8192,\n" +
                "        \"hot_add_enabled\": false,\n" +
                "        \"hot_add_limit_MiB\": 8192\n" +
                "    },\n" +
                "    \"disks\": {\n" +
                "        \"2000\": {\n" +
                "            \"scsi\": {\n" +
                "                \"bus\": 0,\n" +
                "                \"unit\": 0\n" +
                "            },\n" +
                "            \"backing\": {\n" +
                "                \"vmdk_file\": \"[datastore2] shkim-vsphere-ft6sp/shkim-vsphere-ft6sp.vmdk\",\n" +
                "                \"type\": \"VMDK_FILE\"\n" +
                "            },\n" +
                "            \"label\": \"Hard disk 1\",\n" +
                "            \"type\": \"SCSI\",\n" +
                "            \"capacity\": 26843545600\n" +
                "        }\n" +
                "    },\n" +
                "    \"parallel_ports\": {},\n" +
                "    \"sata_adapters\": {},\n" +
                "    \"cpu\": {\n" +
                "        \"hot_remove_enabled\": false,\n" +
                "        \"count\": 4,\n" +
                "        \"hot_add_enabled\": false,\n" +
                "        \"cores_per_socket\": 4\n" +
                "    },\n" +
                "    \"scsi_adapters\": {\n" +
                "        \"1000\": {\n" +
                "            \"pci_slot_number\": 160,\n" +
                "            \"scsi\": {\n" +
                "                \"bus\": 0,\n" +
                "                \"unit\": 7\n" +
                "            },\n" +
                "            \"label\": \"SCSI controller 0\",\n" +
                "            \"sharing\": \"NONE\",\n" +
                "            \"type\": \"PVSCSI\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"power_state\": \"POWERED_ON\",\n" +
                "    \"floppies\": {},\n" +
                "    \"identity\": {\n" +
                "        \"name\": \"shkim-vsphere-ft6sp\",\n" +
                "        \"instance_uuid\": \"fde45a99-fb43-49fb-a993-ad352f657fc3\",\n" +
                "        \"bios_uuid\": \"421b5fd0-3c85-3e3e-4a76-96de033de204\"\n" +
                "    },\n" +
                "    \"nvme_adapters\": {},\n" +
                "    \"name\": \"shkim-vsphere-ft6sp\",\n" +
                "    \"nics\": {\n" +
                "        \"4000\": {\n" +
                "            \"start_connected\": true,\n" +
                "            \"pci_slot_number\": 192,\n" +
                "            \"backing\": {\n" +
                "                \"network_name\": \"VM Network\",\n" +
                "                \"type\": \"STANDARD_PORTGROUP\",\n" +
                "                \"network\": \"network-13\"\n" +
                "            },\n" +
                "            \"mac_address\": \"00:50:56:9b:03:cf\",\n" +
                "            \"mac_type\": \"ASSIGNED\",\n" +
                "            \"allow_guest_control\": true,\n" +
                "            \"wake_on_lan_enabled\": true,\n" +
                "            \"label\": \"Network adapter 1\",\n" +
                "            \"state\": \"CONNECTED\",\n" +
                "            \"type\": \"VMXNET3\",\n" +
                "            \"upt_compatibility_enabled\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"boot\": {\n" +
                "        \"delay\": 0,\n" +
                "        \"retry_delay\": 10000,\n" +
                "        \"enter_setup_mode\": false,\n" +
                "        \"type\": \"BIOS\",\n" +
                "        \"retry\": false\n" +
                "    },\n" +
                "    \"serial_ports\": {},\n" +
                "    \"boot_devices\": [\n" +
                "        {\n" +
                "            \"disks\": [\n" +
                "                \"2000\"\n" +
                "            ],\n" +
                "            \"type\": \"DISK\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"guest_OS\": \"OTHER\",\n" +
                "    \"hardware\": {\n" +
                "        \"upgrade_policy\": \"NEVER\",\n" +
                "        \"upgrade_status\": \"NONE\",\n" +
                "        \"version\": \"VMX_15\"\n" +
                "    }\n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();
        VsphereVM vm = mapper.readValue(jsonData,VsphereVM.class);
        vsphereVMRepository.save(vm);

    }

    public void getToken(String server, String username, String password){

        String loginURL = "https://" + server + "/rest/com/vmware/cis/session";
        try{
            URL url = new URL(loginURL);
            URLConnection connection = url.openConnection();
            HttpURLConnection urlConnection = (HttpURLConnection)connection;
            urlConnection.setRequestMethod("POST");

            String auth= "Basic" + new BASE64Encoder().encode((username + ":" + password).getBytes());

            urlConnection.setRequestProperty("Authorization", auth);


            int responseCode = urlConnection.getResponseCode();

            System.out.println("responseCode: " + responseCode);
        } catch (MalformedURLException malE){
            malE.printStackTrace();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }

    }

}
