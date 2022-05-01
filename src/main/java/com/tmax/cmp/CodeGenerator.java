package com.tmax.cmp;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CodeGenerator {

    public static void main(String[] args){
        String txt = "package com.tmax.cmp.generated;\n" +
                "\n" +
                "import lombok.Builder;\n" +
                "import lombok.Getter;\n" +
                "\n" +
                "@Builder\n" +
                "@Getter\n" +
                "public class Tets2DTO {\n" +
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
                "}\n" ;

        String fileName = "src\\main\\java\\com\\tmax\\cmp\\generated\\TestDTO.java";

        try{

            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

            // 파일안에 문자열 쓰기
            fw.write(txt);
            fw.flush();

            // 객체 닫기
            fw.close();

        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
