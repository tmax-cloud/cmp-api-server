package com.tmax.cmp.entity.aws.ec2;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.Instant;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EbsInstanceBlockDevice {
    private Instant attachTime;

    private Boolean deleteOnTermination;

    private String status;

    private String volumeId;


}
