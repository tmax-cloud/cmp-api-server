package com.tmax.cmp.entity.aws.ec2;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuOptions {

    private Integer coreCount;
    private Integer threadsPerCore;

}
