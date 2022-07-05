package com.tmax.cmp.svc.terraform;

import java.io.*;
import java.net.URL;

public class TerraformProvisioningService {
    public void createResource(String workingDirPath) throws IOException, InterruptedException {
        URL resource = getClass().getClassLoader().getResource("terraform");
        String terraformBinaryPath = resource.getFile().substring(1);

        System.out.println("path : " + terraformBinaryPath);

        //command[0]은 window의 경우 "cmd.exe", linux의 경우 "bash"
        String[] command = new String[] { "cmd.exe", "/c", terraformBinaryPath + "/terraform.exe apply -auto-approve"};
        //String[] command = new String[] { "/bin/bash", "-c", terraformBinaryPath + "/terraform apply -auto-approve"};

        ProcessBuilder builder = new ProcessBuilder(command);

        //builder.directory(new File("C:/Users/Tmax/Desktop/terraform-hcl"));
        builder.directory(new File(workingDirPath));


        //subprocess의 input stream을 parent process의 input stream으로 Redirect.
        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);

        //subprocess의 output stream을 parent process의 output stream으로 Redirect.
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        builder.redirectErrorStream(true);

        try {
            Process childProcess = builder.inheritIO().start(); //child process생성

            InputStreamReader inputReader = new InputStreamReader(childProcess.getInputStream(), "MS949");
            BufferedReader bufferedReader = new BufferedReader(inputReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                }

                System.out.println(line);
            }
            childProcess.getErrorStream().close();
            childProcess.getInputStream().close();
            childProcess.getOutputStream().close();

            childProcess.waitFor();

            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Parent exits.");

    }

}
