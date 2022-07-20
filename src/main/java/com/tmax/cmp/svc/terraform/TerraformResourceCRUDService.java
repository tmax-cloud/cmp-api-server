package com.tmax.cmp.svc.terraform;


import com.tmax.cmp.entity.terraform.TerraformResult;
import com.tmax.cmp.repository.TerraformResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TerraformResourceCRUDService {
    @Autowired
    private TerraformResultRepository terraformResultRepository;




    public TerraformResult getTerraformResult(String vendorName, String resourceName, String region, String content, String state) {
        String createAt;
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter yearMonthDateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yearMonthDate = nowDate.format(yearMonthDateForm);

        LocalTime nowDate2 = LocalTime.now();
        DateTimeFormatter hourMinuteSecondForm = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hourMinuteSecond = nowDate2.format(hourMinuteSecondForm);

        createAt = yearMonthDate + " " + hourMinuteSecond;

        TerraformResult terraformResult = new TerraformResult();
        terraformResult.setVendorName(vendorName);
        terraformResult.setResourceName(resourceName);
        terraformResult.setRegion(region);
        terraformResult.setCreateAt(createAt);
        terraformResult.setContent(content);
        terraformResult.setState(state);
        return terraformResult;
    }

    public void createResource(String workingDirPath, String vendorName, String resourceName, String region) throws IOException, SQLException {
        String content = "";
        String state = "";

        URL resource = getClass().getClassLoader().getResource("terraform");
        String terraformBinaryPath = resource.getFile().substring(1);

        String[] command = new String[] { "cmd.exe", "/c", terraformBinaryPath + "/terraform.exe apply -auto-approve"};

        ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
        builder.directory(new File(workingDirPath));
        builder.redirectOutput(ProcessBuilder.Redirect.PIPE);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Process childProcess = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader;

        try {
            childProcess = builder.start(); //child process생성

            inputStreamReader = new InputStreamReader(childProcess.getInputStream(), "MS949");
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            int pos = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                }
                //이후 소켓 연결해 client로 정보 보내는 logic을 추가할 예정
                System.out.println(line);
                content += line;
            }


            int success = childProcess.waitFor();

            if(success <= 0) {
                state = "create success";
                System.out.println("process successfully executed!!!!!!!! with success code : " + success);
            }
            else{
                Exception e = new Exception("timeout");
                throw e;
            }
            Thread.sleep(5000);
        } catch (IOException e) {
            state = "create fail";
            PrintStream printStream = new PrintStream(out);
            state += " : " + out.toString();
            e.printStackTrace(printStream);
        } catch (InterruptedException e) {
            state = "create fail";
            PrintStream printStream = new PrintStream(out);
            state += " : " + out.toString();
            e.printStackTrace(printStream);
        } catch (Exception e) {
            state = "create fail";
            PrintStream printStream = new PrintStream(out);
            state += " : " + out.toString();
            e.printStackTrace(printStream);
        }
        finally {
            TerraformResult terraformResult;
            terraformResult = getTerraformResult(vendorName, resourceName, region, content, state);

            if(terraformResult == null) throw new IllegalArgumentException("item cannot be null");
            terraformResultRepository.save(terraformResult);

            bufferedReader.close();
            childProcess.getErrorStream().close();
            childProcess.getInputStream().close();
            childProcess.getOutputStream().close();
            childProcess.destroy();
        }
        System.out.println("Parent job done");
    }

    public void deleteResource(String workingDirPath, String vendorName, String resourceName, String region) throws IOException {
        String content = "";
        String state = "";

        FileDescriptor fileDescriptor = new FileDescriptor();
        URL resource = getClass().getClassLoader().getResource("terraform");
        String terraformBinaryPath = resource.getFile().substring(1);

        String[] command = new String[] { "cmd.exe", "/c", terraformBinaryPath + "/terraform.exe destroy -auto-approve"};

        ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
        builder.directory(new File(workingDirPath));
        builder.redirectOutput(ProcessBuilder.Redirect.PIPE);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Process childProcess = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader;

        try {
            childProcess = builder.start(); //child process생성

            inputStreamReader = new InputStreamReader(childProcess.getInputStream(), "MS949");
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            int pos = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                }
                //이후 소켓 연결해 client로 정보 보내는 logic을 추가할 예정
                System.out.println(line);
                content += line;
            }

            int success = childProcess.waitFor();

            if(success <= 0) {
                state = "delete success";
                System.out.println("process successfully executed!!!!!!!! with success code : " + success);
            }
            else{
                Exception e = new Exception("timeout");
                throw e;
            }
            Thread.sleep(5000);
        } catch (IOException e) {
            state = "delete fail";
            PrintStream printStream = new PrintStream(out);
            state += " : " + out.toString();
            e.printStackTrace(printStream);
        } catch (InterruptedException e) {
            state = "delete fail";
            PrintStream printStream = new PrintStream(out);
            state += " : " + out.toString();
            e.printStackTrace(printStream);
        } catch (Exception e) {
            state = "delete fail";
            PrintStream printStream = new PrintStream(out);
            state += " : " + out.toString();
            e.printStackTrace(printStream);
        }
        finally {
            TerraformResult terraformResult;
            terraformResult = getTerraformResult(vendorName, resourceName, region, content, state);

            if(terraformResult == null) throw new IllegalArgumentException("item cannot be null");
            terraformResultRepository.save(terraformResult);
            childProcess.getErrorStream().close();
            childProcess.getInputStream().close();
            childProcess.getOutputStream().close();
            childProcess.destroy();
        }
        System.out.println("Parent job done");
    }
}
