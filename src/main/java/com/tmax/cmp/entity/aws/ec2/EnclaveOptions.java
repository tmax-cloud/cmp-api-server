package com.tmax.cmp.entity.aws.ec2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EnclaveOptions {

    private boolean enabled;

}
