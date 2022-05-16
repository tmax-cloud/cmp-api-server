package com.tmax.cmp.entity.aws.ec2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IamInstanceProfile {

    private String arn;
    private String id;

}
