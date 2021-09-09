package com.tmax.cmp.svc;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicClassBuilder {
    public String buildClass(String className) throws Exception{
        String path = "build/classes/java/main/com/tmax/cmp/";
        //String path = "src/main/java/com/tmax/cmp/";
        //String path = DynamicwasApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        // java 파일 생성
        // 1. DataObject Code
        File dtoFile = new File(path + "generated/" + className + "DTO.java");
        System.out.println("path = "+ dtoFile.getPath());
        dtoFile.getParentFile().mkdirs();
        dtoFile.createNewFile();
        String dtoCode = this.generateCode(className, "dto");
        System.out.println(dtoCode);
        new FileWriter(dtoFile).append(dtoCode).close();

        // 2. Service Code
        File svcFile = new File(path + "generated/" + className + ".java");
        System.out.println("path = "+ svcFile.getPath());
        svcFile.getParentFile().mkdirs();
        svcFile.createNewFile();
        String svcCode = this.generateCode(className, "svc");
        System.out.println(svcCode);
        new FileWriter(svcFile).append(svcCode).close();

        System.out.println("ClassPath:"+System.getProperty("java.class.path"));

        //List<String> options = new ArrayList<String>();
        // set compiler's classpath to be same as the runtime's
        //options.addAll(Arrays.asList("-classpath",System.getProperty("java.class.path")));
        //options.add(dtoFile.getPath());

        // compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //compiler.getTask(null, null, null, options, null, dtoFile.getPath()).call();

        //.run(null, System.out, System.out, options.toArray(new String[] {}));
        compiler.run(null, System.out, System.out, "-parameters", "-classpath", System.getProperty("java.class.path"), dtoFile.getPath());
        compiler.run(null, System.out, System.out, "-parameters", "-classpath", System.getProperty("java.class.path"), svcFile.getPath());

        return className + "is build";
    }
    /*
    public Object createInstance(String className) throws Exception{
        String path = "build/classes/java/main/com/tmax/cmp/";
        //String path = "src/main/java/com/tmax/cmp/";
        //String path = DynamicwasApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        // java 파일 생성
        File sourceFile = new File(path + "generated/" + className + ".java");
        System.out.println("path = "+ sourceFile.getPath());
        sourceFile.getParentFile().mkdirs();
        sourceFile.createNewFile();
        String source = this.generateCode(className);
        System.out.println(source);
        new FileWriter(sourceFile).append(source).close();

        // compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, System.out, System.out, sourceFile.getPath());

        // Move to Class File

        //String classPath = "build/classes/java/main/com/tmax/cmp/";

        //File generateFile = new File (path + "generated/DynamicGeneratedClass.class");
        //File classFile = new File(classPath + "generated/DynamicGeneratedClass.class");
        //generateFile.renameTo(classFile);


        // class load
        URLClassLoader cl = URLClassLoader.newInstance(new URL[] {new File(path + "generated").toURI().toURL()});
        Class<?> cls = Class.forName("com.tmax.cmp.generated."+className, true, cl);

        // instance 생성
        return cls.newInstance();
    }
    */
    public Object runObject(Object obj, String method) throws Exception{
        Method objMethod = obj.getClass().getMethod(method);
        Object result = objMethod.invoke(obj);
        return result;
    }

    private String generateCode(String className, String type){
        StringBuilder sb = new StringBuilder();

        /*
        sb.append("package com.tmax.cmp.generated;\n" +
                "\n" +
                //"import org.springframework.web.bind.annotation.GetMapping;\n" +
                //"import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                //"@RestController\n" +
                "public class " + className + "{\n" +
                //"    @GetMapping(\"/hello\")\n" +
                "    public String myMethod() throws Exception {\n" +
                "        return \"Hellooooooooo~~~!\";\n" +
                "    }\n" +
                "}");
        */
        if (type.equals("dto")) {
            sb.append("package com.tmax.cmp.generated;\n" +
                    "\n" +
                    "import lombok.Builder;\n" +
                    "import lombok.Getter;\n" +
                    "\n" +
                    "@Builder\n" +
                    "@Getter\n" +
                    "public class " + className + "DTO {\n" +
                    "    private String instanceId;\n" +
                    "    private String imageId;\n" +
                    "    private String keyName;\n" +
                    "    private String subnetId;\n" +
                    "    private String vpcId;\n" +
                    "    private String privateIpAddress;\n" +
                    "    private String architecture;\n" +
                    "    private String rootDeviceType;\n" +
                    "    private String rootDeviceName;\n" +
                    "    private String virtualizationType;\n" +
                    "    private String hypervisor;\n" +
                    "}\n");
        } else if (type.equals("svc")) {
            sb.append("package com.tmax.cmp.generated;\n" +
                    "\n" +
                    //"import org.springframework.web.bind.annotation.GetMapping;\n" +
                    //"import org.springframework.web.bind.annotation.RestController;\n" +
                    "\n" +
                    //"@RestController\n" +
                    "public class " + className + "{\n" +
                    //"    @GetMapping(\"/hello\")\n" +
                    "    public String myMethod() throws Exception {\n" +
                    "        return \"Hellooooooooo~~~!\";\n" +
                    "    }\n" +
                    "}");
        }
        return sb.toString();
    }
}
