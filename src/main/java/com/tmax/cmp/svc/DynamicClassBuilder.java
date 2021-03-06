package com.tmax.cmp.svc;

//import javax.security.auth.login.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

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

        // 3. Repository Code
        File repoFile = new File(path + "generated/" + className + "Repository.java");
        System.out.println("path = "+ repoFile.getPath());
        repoFile.getParentFile().mkdirs();
        repoFile.createNewFile();
        String repoCode = this.generateCode(className, "repo");
        System.out.println(repoCode);
        new FileWriter(repoFile).append(repoCode).close();

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
        compiler.run(null, System.out, System.out, "-parameters", "-classpath", System.getProperty("java.class.path"), repoFile.getPath());
        compiler.run(null, System.out, System.out, "-parameters", "-classpath", System.getProperty("java.class.path"), svcFile.getPath());

        // Generate table dynamically
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sys");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "tmax@23");
        //config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        config.addAnnotatedClass(Class.forName("com.tmax.cmp.generated."+className+"DTO"));

        try {
            SessionFactory sessionFactory = config.buildSessionFactory();
            /*
            Session session = sessionFactory.openSession();

                // Do something here if you need....

            session.close();
            */
            sessionFactory.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return className + " is build";
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
                    "import javax.persistence.Entity;\n" +
                    "import javax.persistence.GeneratedValue;\n" +
                    "import javax.persistence.GenerationType;\n" +
                    "import javax.persistence.Id;\n" +
                    "\n" +
                    "@Entity\n" +
                    "//@Table(name=" + className + ")\n" +
                    "@Getter\n" +
                    "@Builder\n" +
                    "public class " + className + "DTO {\n" +
                    "\n" +
                    "    @Id\n" +
                    "    @GeneratedValue(strategy = GenerationType.AUTO)\n" +
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
                    "}\n"
            );
        } else if (type.equals("svc")) {
            sb.append("package com.tmax.cmp.generated;\n" +
                    "\n" +
                    "import com.amazonaws.regions.Regions;\n" +
                    "import com.tmax.cmp.generated." + className + "DTO;\n" +
                    "import org.springframework.stereotype.Service;\n" +
                    "import com.tmax.cmp.generated." + className + "Repository;\n" +
                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import com.amazonaws.services.ec2.AmazonEC2;\n" +
                    "import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;\n" +
                    "import com.amazonaws.services.ec2.model.DescribeInstancesRequest;\n" +
                    "import com.amazonaws.services.ec2.model.DescribeInstancesResult;\n" +
                    "import com.amazonaws.services.ec2.model.Instance;\n" +
                    "import com.amazonaws.services.ec2.model.Reservation;\n" +
                    "import java.util.ArrayList;\n" +
                    "import java.util.List;\n" +
                    "public class " + className + " {\n" +
                    "    @Autowired\n" +
                    "    " + className + "Repository " + className + "Repository;\n" +
                    "    public " + className + "DTO myMethod(final String query, final String region) {\n" +
                    "        List<Instance> instances = describeInstances(region);\n" +
                    "\n" +
                    "        for (Instance instance: instances) {\n" +
                    "            if (query.equals(instance.getInstanceId())){\n" +
                    "                return " + className + "DTO.builder().\n" +
                    "                        instanceId(instance.getInstanceId()).\n" +
                    "                        imageId(instance.getImageId()).\n" +
                    "                        keyName(instance.getKeyName()).\n" +
                    "                        subnetId(instance.getSubnetId()).\n" +
                    "                        vpcId(instance.getVpcId()).\n" +
                    "                        privateIpAddress(instance.getPrivateIpAddress()).\n" +
                    "                        architecture(instance.getArchitecture()).\n" +
                    "                        rootDeviceType(instance.getRootDeviceType()).\n" +
                    "                        rootDeviceName(instance.getRootDeviceName()).\n" +
                    "                        virtualizationType(instance.getVirtualizationType()).\n" +
                    "                        hypervisor(instance.getHypervisor()).build();\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return " + className + "DTO.builder().instanceId(\"test123\").imageId(\"asdf\").build();\n" +
                    "    }\n" +
                    "    public List<Instance> describeInstances(String region)\n" +
                    "    {\n" +
                    "        //final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();\n" +
                    "        AmazonEC2 ec2;\n" +
                    "        if (region == null) {\n" +
                    "            ec2 = AmazonEC2ClientBuilder.standard()\n" +
                    "                    .withRegion(Regions.AP_NORTHEAST_2)\n" +
                    "                    .build();\n" +
                    "        } else {\n" +
                    "            ec2 = AmazonEC2ClientBuilder.standard()\n" +
                    "                    .withRegion(Regions.valueOf(region))\n" +
                    "                    .build();\n" +
                    "        }\n" +
                    "        boolean done = false;\n" +
                    "\n" +
                    "        DescribeInstancesRequest request = new DescribeInstancesRequest();\n" +
                    "        List<Instance> instances = new ArrayList<>();\n" +
                    "\n" +
                    "        while(!done) {\n" +
                    "            DescribeInstancesResult response = ec2.describeInstances(request);\n" +
                    "\n" +
                    "            for(Reservation reservation : response.getReservations()) {\n" +
                    "                for(Instance instance : reservation.getInstances()) {\n" +
                    "                    instances.add(instance);\n" +
                    "                }\n" +
                    "            }\n" +
                    "\n" +
                    "            request.setNextToken(response.getNextToken());\n" +
                    "\n" +
                    "            if(response.getNextToken() == null) {\n" +
                    "                done = true;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return instances;\n" +
                    "    }\n" +
                    "   public List<" + className + "DTO> getByInstanceId(String id)\n" +
                    "   {\n" +
                    "       return " + className + "Repository.findByInstanceId(\"aaa\");\n" +
                    "   }\n" +
                    "}");
        } else if (type.equals("repo")) {
            sb.append("package com.tmax.cmp.generated;\n" +
                    "\n" +
                    "import com.tmax.cmp.generated." + className + "DTO;\n" +
                    "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                    "import org.springframework.data.repository.query.Param;\n" +
                    "import java.util.List;\n" +
                    "\n" +
                    "//@EnableJpaRepositories(basePackages = {com.tmax.cmp.generated})\n" +
                    "public interface " + className + "Repository extends JpaRepository<" + className + "DTO, String> {\n" +
                    "    List<" + className + "DTO> findByInstanceId(@Param(\"instanceId\") String instanceId);\n" +
                    "}");
        }
        return sb.toString();
    }
}

