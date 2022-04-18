/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.tmax.cmp.test;



/**
 * Describes all EC2 instances associated with an AWS account
 */
public class DescribeInstances
{
//    public static void main(String[] args)
//    //public void print()
//    {
//       //final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();
//        AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
//                .withRegion(Regions.US_EAST_2)
//                .build();
//        boolean done = false;
//
//        DescribeInstancesRequest request = new DescribeInstancesRequest();
//        while(!done) {
//            DescribeInstancesResult response = ec2.describeInstances(request);
//
//            for(Reservation reservation : response.getReservations()) {
//                for(Instance instance : reservation.getInstances()) {
//                    System.out.printf(
//                            "Found instance with id %s, " +
//                                    "AMI %s, " +
//                                    "type %s, " +
//                                    "state %s " +
//                                    "and monitoring state %s",
//                            instance.getInstanceId(),
//                            instance.getImageId(),
//                            instance.getInstanceType(),
//                            instance.getState().getName(),
//                            instance.getMonitoring().getState());
//                }
//            }
//
//            request.setNextToken(response.getNextToken());
//
//            if(response.getNextToken() == null) {
//                done = true;
//            }
//        }
//    }
}