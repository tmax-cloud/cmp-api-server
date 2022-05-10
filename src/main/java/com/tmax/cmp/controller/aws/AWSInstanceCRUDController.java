package com.tmax.cmp.controller.aws;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.aws.ec2.Ec2Instance;
import com.tmax.cmp.entity.common.client.Client;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import com.tmax.cmp.svc.aws.AWSInstanceService;
import com.vmware.vcenter.VMTypes;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aws/instance")

public class AWSInstanceCRUDController {

    @Autowired
    private AWSInstanceService awsInstanceService;

    @GetMapping("/sync")
    public void syncInstance(@RequestParam(name = "region", required = false) String region){

        ArrayList<Ec2Instance> instanceList = awsInstanceService.getAllInstanceFromAWS(region);

        for(Ec2Instance ec2Instance : instanceList){
            System.out.println("save instance: " + ec2Instance.getInstanceId());
        }

    }

    @GetMapping("/get")
    public Ec2Instance get(@RequestParam(name = "region", required = false) String region) {

        List<Ec2Instance> instances = awsInstanceService.getInstanceByRegion(region);

        for(Ec2Instance instance : instances){
            System.out.println("id: " + instance.getInstanceId());
        }

        return null;
    }

    @GetMapping("/amis")
    public void amis(@RequestParam(name = "region", required = false) String region,
                     @RequestParam(name = "keyName", required = false) String keyName,
                     @RequestParam(name = "value", required = false) String value) {

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();
        awsInstanceService.searchAMI(ec2Client, keyName, value);

    }

    @GetMapping("/test")
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> clients = (ArrayList<awsClient>)context.getBean("awsClients");

        for(awsClient client : clients) {
            boolean done = false;
            String nextToken = null;
            ArrayList<Ec2Instance> awsInstanceDTOS = new ArrayList<Ec2Instance>();
            try{
                do{
                    DescribeInstancesRequest request = DescribeInstancesRequest
                            .builder().maxResults(10).nextToken(nextToken).build();
                    DescribeInstancesResponse response = client.getEc2Client().describeInstances(request);

                    for(Reservation reservation : response.reservations()){

                        for(software.amazon.awssdk.services.ec2.model.Instance instance : reservation.instances()){
                            awsInstanceDTOS.add(Ec2Instance.builder().
                                    amiLaunchIndex(instance.amiLaunchIndex()).
                                    imageId(instance.imageId()).
                                    instanceId(instance.instanceId()).
                                    instanceType(instance.instanceTypeAsString()).
                                    kernelId(instance.kernelId()).
                                    keyName(instance.keyName()).
                                    launchTime(instance.launchTime()).
                                    placement(instance.placement()).
                                    platform(instance.platformAsString()).
                                    privateDnsName(instance.privateDnsName()).
                                    privateIpAddress(instance.privateIpAddress()).
                                    publicDnsName(instance.publicDnsName()).
                                    ramdiskId(instance.ramdiskId()).
                                    stateReason(instance.stateReason()).
                                    state(instance.state().nameAsString()).
                                    stateTransitionReason(instance.stateTransitionReason()).
                                    subnetId(instance.subnetId()).
                                    vpcId(instance.vpcId()).
                                    architecture(instance.architectureAsString()).
                                    clientToken(instance.clientToken()).
                                    ebsOptimized(instance.ebsOptimized()).
                                    enaSupport(instance.enaSupport()).
                                    hypervisor(instance.hypervisorAsString()).
                                    iamInstanceProfile(instance.iamInstanceProfile()).
                                    instanceLifecycle(instance.instanceLifecycleAsString()).
                                    outpostArn(instance.outpostArn()).
                                    rootDeviceName(instance.rootDeviceName()).
                                    rootDeviceType(instance.rootDeviceTypeAsString()).
                                    sourceDestCheck(instance.sourceDestCheck()).
                                    spotInstanceRequestId(instance.spotInstanceRequestId()).
                                    sriovNetSupport(instance.sriovNetSupport()).
                                    virtualizationType(instance.virtualizationTypeAsString()).
                                    cpuOptions(instance.cpuOptions()).
                                    capacityReservationSpecification(instance.capacityReservationSpecification()).
                                    hibernationOptions(instance.hibernationOptions()).
                                    metadataOptions(instance.metadataOptions()).
                                    enclaveOptions(instance.enclaveOptions()).
                                    bootMode(instance.bootModeAsString()).build());


                            System.out.println("Region is " + client.getRegion() );
                            System.out.println("Instance Id is " + instance.instanceId());
                            System.out.println("state as string: " + instance.state().nameAsString());
                            System.out.println("state: " + instance.state());
                            System.out.println("state name: " + instance.state().name());
                            System.out.println("--------------------------------------");
                        }
                    }
                } while(nextToken != null);
            }catch (Ec2Exception e){
                System.err.println(e.awsErrorDetails().errorMessage());
                System.exit(1);
            }
        }


        List<vSphereClient> vclients = (ArrayList<vSphereClient>)context.getBean("vSphereClients");
        for(vSphereClient client : vclients) {

            List<VMTypes.Summary> vmList = client.getVmClient().list(new VMTypes.FilterSpec());
            for (VMTypes.Summary vmSummary : vmList) {
                System.out.println(vmSummary);
            }
        }
    }
}