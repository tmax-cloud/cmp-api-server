package com.tmax.cmp.controller;

import com.tmax.cmp.svc.DynamicClassBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@RestController
@RequestMapping(value="/{className}", method= RequestMethod.GET)
public class CMPController {
    @GetMapping("/generate")
    public String generateCode(@PathVariable String className) throws Exception {
        // make instance
        DynamicClassBuilder dcb = new DynamicClassBuilder();
        /*
        String body = "System.out.println(\"Hello World!\");\n" +
                "return \"Hello World!\";";
        */

        //Object instance = dcb.createInstance(className);
        String result = dcb.buildClass(className);

        return result;
    }

    @GetMapping("/execute")
    public String handleRequest(@PathVariable String className, @RequestParam String awsid, @RequestParam String region) throws Exception {
        String path = "build/classes/java/main/com/tmax/cmp/";

        // class load
        URLClassLoader cl = URLClassLoader.newInstance(new URL[] {new File(path + "generated").toURI().toURL()});
        Class<?> cls = Class.forName("com.tmax.cmp.generated." + className, true, cl);

        Object obj = cls.newInstance();
        Class argument_awsid = awsid.getClass();
        Class argument_region = region.getClass();

        Method objMethod = obj.getClass().getMethod("myMethod", argument_awsid, argument_region);
        Object result = objMethod.invoke(obj, awsid, null);
        return result.toString();
    }
}

