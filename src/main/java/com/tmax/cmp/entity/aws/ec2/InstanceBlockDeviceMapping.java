package com.tmax.cmp.entity.aws.ec2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InstanceBlockDeviceMapping {

    private String deviceName;

    @Embedded
    private EbsInstanceBlockDevice ebs;
}
