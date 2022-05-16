package com.tmax.cmp.entity.aws.ec2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstanceNetworkInterfaceAttachment {

    private Instant attachTime;

    private String attachmentId;

    private Boolean deleteOnTermination;

    private Integer deviceIndex;

    private String status;

    private Integer networkCardIndex;
}
