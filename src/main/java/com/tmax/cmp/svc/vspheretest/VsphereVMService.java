package com.tmax.cmp.svc.vspheretest;

import com.google.gson.*;
import com.tmax.cmp.entity.vsphere.vm.vmresources.VsphereVM;
import com.tmax.cmp.repository.VsphereVMRepository;
import com.vmware.vapi.bindings.StubConfiguration;
import com.vmware.vapi.bindings.StubFactory;
import com.vmware.vcenter.VM;
import com.vmware.vcenter.VMTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public void saveVsphereVMInfo(String jsonData) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException,
            ClassNotFoundException, InstantiationException {

        jsonData = "{    \n" +
//                "\"disks\": {\n" +
//                "        \"2000\": {\n" +
//                "            \"scsi\": {\n" +
//                "                \"bus\": 0,\n" +
//                "                \"unit\": 0\n" +
//                "            },\n" +
//                "            \"backing\": {\n" +
//                "                \"vmdk_file\": \"[datastore3] cmp-test/cmp-test.vmdk\",\n" +
//                "                \"type\": \"VMDK_FILE\"\n" +
//                "            },\n" +
//                "            \"label\": \"Hard disk 1\",\n" +
//                "            \"type\": \"SCSI\",\n" +
//                "            \"capacity\": 33285996544\n" +
//                "        },\n" +
//                "        \"2001\": {\n" +
//                "            \"scsi\": {\n" +
//                "                \"bus\": 0,\n" +
//                "                \"unit\": 1\n" +
//                "            },\n" +
//                "            \"backing\": {\n" +
//                "                \"vmdk_file\": \"[datastore3] cmp-test/cmp-test_1.vmdk\",\n" +
//                "                \"type\": \"VMDK_FILE\"\n" +
//                "            },\n" +
//                "            \"label\": \"Hard disk 2\",\n" +
//                "            \"type\": \"SCSI\",\n" +
//                "            \"capacity\": 34359738368\n" +
//                "        }\n" +
//                "    },\n" +
                "\"power_state\": \"POWERED_ON\",\n" +
                "\"instant_clone_frozen\": true,\n" +
                "\"name\": \"test-node\",\n" +
                "\"identity\": {\n" +
                "        \"name\": \"shkim-vsphere-md-0-85f84c5646-85mfl\",\n" +
                "        \"instance_uuid\": \"5bd9deae-b981-47dc-999b-bf0ff3c2d047\",\n" +
                "        \"bios_uuid\": \"421b4834-d3ff-a7d3-aacc-6fb1fe942181\"\n" +
                "    }\n" +
                "}";
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(jsonData);

        JsonObject jsonObject = (JsonObject)obj;
        Gson gson = new Gson();

        System.out.println("=======disk key value==========");

        VsphereVM vm = new VsphereVM();

        //keyname 찾기
        for(String keyName: jsonObject.keySet()){

            System.out.println("key: " + keyName);
            //다음 value가 json이면
            if(jsonObject.get(keyName).isJsonObject()){

                System.out.println("value is json");
                String className = upperCaseFirst(keyName);
                //value of Json
//                String className = keyName;
                JsonElement jsonVal = jsonObject.get(keyName);
                //Class of key
                Class<?> testClass = Class.forName("com.tmax.cmp.entity.vsphere.vm.vmresources." + className);
                //create Class instance of key
                Object newObj = testClass.newInstance();
                //get setter method name
                String methodName = getSetterName(upperCaseFirst(keyName));
                //method of setter
                Method setterMethod;

                Object inputVal = testClass.newInstance();
                inputVal = gson.fromJson(jsonVal,newObj.getClass());
                try{
                    setterMethod = vm.getClass().getDeclaredMethod(methodName, newObj.getClass());
                    setterMethod.invoke(vm,inputVal);
                } catch (NoSuchMethodException e) {
                    setterMethod = newObj.getClass().getDeclaredMethod(methodName, newObj.getClass());
                    setterMethod.invoke(newObj,inputVal);
                }


            }else{ //value가 json이 아니면
                System.out.println("value is not a json");
                Object valType;
                JsonPrimitive jsonVal = jsonObject.get(keyName).getAsJsonPrimitive();

                if(jsonVal.isNumber()){
                    System.out.println("jsonVal : int");
                    valType = jsonVal.getAsInt();
                }else if(jsonVal.isBoolean()){
                    System.out.println("jsonVal : bool");
                    valType = jsonVal.getAsBoolean();
                }else{
                    System.out.println("jsonVal: String");
                    valType = jsonVal.getAsString();
                }

                //키이름으로 메서드 찾아서 setter로 변경하기
                String methodName = getSetterName(upperCaseFirst(keyName));

                Method method = vm.getClass().getDeclaredMethod(methodName,valType.getClass());
                method.invoke(vm,valType);

            }

        }

        System.out.println(vm.toString());
        vsphereVMRepository.save(vm);

    }



    public static String getSetterName(String keyName){

        String methodName = "set" + keyName;
//        methodName = TiberoUtils.convertUnderscoreNameToPropertyName(methodName);

        return methodName;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
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

class TiberoUtils extends JdbcUtils{

}
