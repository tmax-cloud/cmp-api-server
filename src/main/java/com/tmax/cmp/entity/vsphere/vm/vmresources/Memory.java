package com.tmax.cmp.entity.vsphere.vm.vmresources;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Memory {

    private long size_MiB;
    private boolean hot_add_enabled;
    private Long hot_add_increment_size_MiB;
    private Long hot_add_limit_MiB;
}
