package com.tmax.cmp.svc.terraform;


import java.io.*;
import java.net.URL;

public class TerraformProvisioningService {
    public void createResource(String workingDirPath) throws IOException, InterruptedException, NoSuchFieldException {

//        System.out.println("Children Processes:");
//        Optional<ProcessHandle> processHandle = ProcessHandle.allProcesses().findFirst();
//        processHandle.ifPresent(proc -> proc.children().forEach(child -> System.out.println("PID: [ " + child.pid() + " ], Cmd: [ " + child.info().command() + " ]")));

        URL resource = getClass().getClassLoader().getResource("terraform");
        String terraformBinaryPath = resource.getFile().substring(1);

        System.out.println("path : " + terraformBinaryPath);

        //command[0]은 window의 경우 "cmd.exe", linux의 경우 "bash"
        String[] command = new String[] { "cmd.exe", "/c", terraformBinaryPath + "/terraform.exe destroy -auto-approve"};
        //String[] command = new String[] { "/bin/bash", "-c", terraformBinaryPath + "/terraform apply -auto-approve"};

//        String[] command = new String[] {
//                "cmd.exe", "/c", "@echo off\n" +
//                ":_loop\n" +
//                "set /a a+=1\n" +
//                "echo %a%\n" +
//                "goto _loop"
//        };

        ProcessBuilder builder = new ProcessBuilder(command);

        //builder.directory(new File("C:/Users/Tmax/Desktop/terraform-hcl"));
        builder.directory(new File(workingDirPath));


        //subprocess의 input stream을 parent process의 input stream으로 Redirect.
        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);

        //subprocess의 output stream을 parent process의 output stream으로 Redirect.
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        builder.redirectErrorStream(true);
        Process childProcess = null;
        try {
            childProcess = builder.inheritIO().start(); //child process생성
            System.out.println("child process start =========> : " + childProcess.pid());



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

            int success = childProcess.waitFor();

            if(success <= 0)
                System.out.println("process successfully executed!!!!!!!! with success code : " + success);
            else{
                Exception e = new Exception("timeout");
                throw e;
            }
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("child process finished : " + childProcess.pid());
            childProcess.destroy();
        }
        System.out.println("Parent exits.");
    }
}
